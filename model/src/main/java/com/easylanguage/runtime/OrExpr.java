package com.easylanguage.runtime;

public class OrExpr implements IExpr {
    private IExpr left, right;

    public OrExpr(IExpr left, IExpr right) {
        this.left = left;
        this.right = right;
    }

        @Override
        public int eval(SymbolTable symbols) {
            int leftValue = left.eval(symbols);
            int rightValue = right.eval(symbols);
            return (leftValue != 0 || rightValue != 0) ? 1 : 0;
        }
}