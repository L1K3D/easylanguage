package com.easylanguage.runtime;

public class DivExpr implements IExpr {
    private IExpr left, right;

    public DivExpr(IExpr left, IExpr right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public int eval(SymbolTable symbols) {
        int l = left.eval(symbols);
        int r = right.eval(symbols);
        if (r == 0) throw new RuntimeException("Divisão por zero!");
        return l / r;
    }
}