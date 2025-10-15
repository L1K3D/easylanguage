package com.easylanguage;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import com.easylanguage.generated.EasyLanguageLexer;
import com.easylanguage.generated.EasyLanguageParser;
import com.easylanguage.runtime.Interpreter;

public class Main {
    public static void main(String[] args) throws Exception {
        // Código de teste em EasyLanguage
        String code = "int numero; numero = 42; escreva(numero);";

        // Cria o lexer e o parser
        EasyLanguageLexer lexer = new EasyLanguageLexer(CharStreams.fromString(code));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        EasyLanguageParser parser = new EasyLanguageParser(tokens);

        // Analisa o programa
        ParseTree tree = parser.program();

        ParseTreeWalker walker = new ParseTreeWalker();
        Interpreter interpreter = new Interpreter();
        walker.walk(interpreter, tree);

        // Mostra a árvore sintática
        System.out.println("Árvore sintática: " + tree.toStringTree(parser));
    }
}