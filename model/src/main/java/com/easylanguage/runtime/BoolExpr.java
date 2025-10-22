package com.easylanguage.runtime;

public class BoolExpr implements IExpr {
    private boolean value;

    public BoolExpr(boolean value) {
        this.value = value;
    }

    @Override
    public int eval(SymbolTable symbols) {
        return value ? 1 : 0;
    }
}