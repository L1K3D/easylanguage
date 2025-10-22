package com.easylanguage.runtime;

public class VarExpr implements IExpr {
    private String name;

    public VarExpr(String name) {
        this.name = name;
    }

    @Override
    public Object eval(SymbolTable symbols) {
        return symbols.get(name);
    }
}