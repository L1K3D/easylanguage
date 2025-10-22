package com.easylanguage.runtime;

public class SubExpr implements IExpr {
    private IExpr left, right;

    public SubExpr(IExpr left, IExpr right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public int eval(SymbolTable symbols) {
        int l = left.eval(symbols);
        int r = right.eval(symbols);
        return l - r;
    }
}