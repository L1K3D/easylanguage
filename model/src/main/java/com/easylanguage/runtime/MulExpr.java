package com.easylanguage.runtime;

public class MulExpr implements IExpr {
    private IExpr left, right;

    public MulExpr(IExpr left, IExpr right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public int eval(SymbolTable symbols) {
        int l = left.eval(symbols);
        int r = right.eval(symbols);
        return l * r;
    }
}