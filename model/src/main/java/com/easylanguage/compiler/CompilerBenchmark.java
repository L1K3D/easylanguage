package com.easylanguage.compiler;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import com.easylanguage.generated.EasyLanguageLexer;
import com.easylanguage.generated.EasyLanguageParser;
import com.easylanguage.runtime.Interpreter;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;

public class CompilerBenchmark {
    private static final String TEMP_DIR = System.getProperty("java.io.tmpdir");

    public static void comparePerformance(String code, int iterations) throws Exception {
        System.out.println("=== Comparação de Performance ===");
        System.out.println("Código a ser executado:");
        System.out.println(code);
        System.out.println("\nExecutando " + iterations + " iterações...\n");

        // Mede tempo da execução interpretada
        long interpretedTime = measureInterpreted(code, iterations);
        
        // Mede tempo da execução compilada
        long compiledTime = measureCompiled(code, iterations);

        // Mostra resultados
        System.out.println("\nResultados:");
        System.out.println("Tempo médio (interpretado): " + (interpretedTime / iterations) + "ms");
        System.out.println("Tempo médio (compilado): " + (compiledTime / iterations) + "ms");
        System.out.println("Speedup: " + String.format("%.2fx", (double)interpretedTime / compiledTime));
    }

    private static long measureInterpreted(String code, int iterations) throws Exception {
        System.out.println("Medindo execução interpretada...");
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < iterations; i++) {
            // Cria o lexer e o parser
            EasyLanguageLexer lexer = new EasyLanguageLexer(CharStreams.fromString(code));
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            EasyLanguageParser parser = new EasyLanguageParser(tokens);

            // Analisa o programa
            ParseTree tree = parser.program();

            // Executa com o interpretador
            ParseTreeWalker walker = new ParseTreeWalker();
            Interpreter interpreter = new Interpreter();
            walker.walk(interpreter, tree);
        }

        return System.currentTimeMillis() - startTime;
    }

    private static long measureCompiled(String code, int iterations) throws Exception {
        System.out.println("Gerando código Java...");
        
        // Gera o código Java
        EasyLanguageLexer lexer = new EasyLanguageLexer(CharStreams.fromString(code));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        EasyLanguageParser parser = new EasyLanguageParser(tokens);
        ParseTree tree = parser.program();

        JavaCodeGenerator generator = new JavaCodeGenerator();
        String javaCode = generator.generate(tree);

        // Salva o código em um arquivo temporário
        Path tempDir = Path.of(TEMP_DIR, "easylanguage");
        Files.createDirectories(tempDir);
        File sourceFile = new File(tempDir.toFile(), "GeneratedProgram.java");
        try (FileWriter writer = new FileWriter(sourceFile)) {
            writer.write(javaCode);
        }

        // Compila o código
        System.out.println("Compilando código gerado...");
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        compiler.run(null, null, null, sourceFile.getPath());

        // Carrega e executa a classe compilada
        System.out.println("Executando código compilado...");
        URLClassLoader classLoader = URLClassLoader.newInstance(new URL[] { tempDir.toUri().toURL() });
        Class<?> cls = Class.forName("GeneratedProgram", true, classLoader);
        
        long startTime = System.currentTimeMillis();
        
        for (int i = 0; i < iterations; i++) {
            cls.getMethod("main", String[].class).invoke(null, (Object) new String[0]);
        }

        long totalTime = System.currentTimeMillis() - startTime;

        // Limpa os arquivos temporários
        sourceFile.delete();
        new File(tempDir.toFile(), "GeneratedProgram.class").delete();
        tempDir.toFile().delete();

        return totalTime;
    }
}