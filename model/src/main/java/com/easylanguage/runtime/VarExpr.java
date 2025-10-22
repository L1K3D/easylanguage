package com.easylanguage.runtime;

public class VarExpr implements IExpr {
    private String name;

    public VarExpr(String name) {
        this.name = name;
    }

    @Override
    public int eval(SymbolTable symbols) {
        return (Integer) symbols.get(name);
    }
}