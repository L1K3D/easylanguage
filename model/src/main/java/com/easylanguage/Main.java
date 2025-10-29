package com.easylanguage;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import com.easylanguage.generated.EasyLanguageLexer;
import com.easylanguage.generated.EasyLanguageParser;
import com.easylanguage.runtime.Interpreter;
import com.easylanguage.compiler.CompilerBenchmark;

public class Main {
    // Código de teste para benchmark: gera a sequência de Fibonacci iterativamente
    // (sem funções/if, para compatibilidade com o gerador atual)
    private static final String BENCHMARK_CODE = """
            int a;
            int b;
            int tmp;
            int j;
            a = 0;
            b = 1;
            para j de 0 ate 10 passo 1 faca
                escreva(a);
                tmp = a + b;
                a = b;
                b = tmp;
            fim
            """;

    // Método utilitário para rodar qualquer código EasyLanguage
    public static void runTest(String titulo, String code) throws Exception {
        System.out.println("=== " + titulo + " ===");
        System.out.println("Código: " + code);

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

        // Mostra a árvore sintática
        System.out.println("Árvore sintática: " + tree.toStringTree(parser));
        System.out.println();
    }

    public static void main(String[] args) throws Exception {
        // Teste simples
        runTest("Teste simples", "int numero; numero = 42; escreva(numero);");

        // Teste de laço para
        runTest("Teste de laço PARA", "int i; para i de 1 ate 5 passo 2 faca escreva(i);");

        // Teste booleanos
        runTest("Teste booleanos", "boolean a; boolean b; a = verdadeiro; b = falso; escreva(a e nao b); escreva(a ou b);");

        // Teste arrays
        runTest("Teste arrays", "int v[3]; v[0] = 10; v[1] = 20; v[2] = 30; escreva(v[1]);");

        // Teste enquanto
        runTest("Teste ENQUANTO", "int i; i = 0; enquanto (i < 3) faca escreva(i); i = i + 1;");

        // Teste de funções e escopo
        runTest("Teste de funções e escopo", """
            func soma(int a, int b) : int faca
                int resultado;
                resultado = a + b;
                return resultado;
            fim

            proc imprime(int x) faca
                escreva(x);
            fim

            int x;
            x = 10;
            imprime(soma(x, 5));
            imprime(x);
        """);

        // Testes do sistema de tipos
        runTest("Teste do sistema de tipos", """
            int a;
            boolean b;
            int numeros[3];

            a = 42;
            b = verdadeiro;
            numeros[0] = 10;

            escreva(a + 5);
            escreva(b e verdadeiro);
            escreva(a > 20);

            func calculaMedia(int x, int y) : int faca
                return (x + y) / 2;
            fim

            int resultado;
            resultado = calculaMedia(a, numeros[0]);
            escreva(resultado);
        """);
        
            // Teste de performance - comparação entre interpretado e compilado
            System.out.println("\n=== Teste de Performance ===");
            CompilerBenchmark.comparePerformance(BENCHMARK_CODE, 5);
    }
}