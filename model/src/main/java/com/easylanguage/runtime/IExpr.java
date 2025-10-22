package com.easylanguage.runtime;

public interface IExpr {
    // Avalia a expressão e retorna um inteiro: 0 = false, !=0 = true (ou valor numérico)
    int eval(SymbolTable symbols);

    // Helpers para compatibilidade com o código existente
    default int evalAsInt(SymbolTable symbols) {
        return eval(symbols);
    }

    default boolean evalAsBool(SymbolTable symbols) {
        return eval(symbols) != 0;
    }
}