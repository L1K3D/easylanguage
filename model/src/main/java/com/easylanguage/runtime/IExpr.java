package com.easylanguage.runtime;

public interface IExpr {
    Object eval(SymbolTable symbols);

    default int evalAsInt(SymbolTable symbols) {
        return (Integer) eval(symbols);
    }

    default boolean evalAsBool(SymbolTable symbols) {
        Object val = eval(symbols);
        if (val instanceof Boolean) return (Boolean) val;
        return ((Integer) val) != 0;
    }
}