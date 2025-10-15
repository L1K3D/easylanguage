package com.easylanguage.runtime;

public interface IExpr {
    int eval(SymbolTable symbols);
}