package com.easylanguage.runtime;

public class OrExpr implements IExpr {
    private IExpr left, right;

    public OrExpr(IExpr left, IExpr right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public Object eval(SymbolTable symbols) {
        Object l = left.eval(symbols);
        Object r = right.eval(symbols);

        boolean leftValue;
        boolean rightValue;

        if (l instanceof Boolean) {
            leftValue = (Boolean) l;
        } else {
            leftValue = ((Integer) l) != 0; // trata int como booleano
        }

        if (r instanceof Boolean) {
            rightValue = (Boolean) r;
        } else {
            rightValue = ((Integer) r) != 0;
        }

        return leftValue || rightValue;
    }
}