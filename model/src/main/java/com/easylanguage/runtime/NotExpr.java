package com.easylanguage.runtime;

public class NotExpr implements IExpr {
    private IExpr expr;

    public NotExpr(IExpr expr) {
        this.expr = expr;
    }

    @Override
    public int eval(SymbolTable symbols) {
        int val = expr.eval(symbols);
        return (val == 0) ? 1 : 0;
    }
}