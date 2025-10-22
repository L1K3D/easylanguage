package com.easylanguage.runtime;

public class NotExpr implements IExpr {
    private IExpr expr;

    public NotExpr(IExpr expr) {
        this.expr = expr;
    }

    @Override
    public Object eval(SymbolTable symbols) {
        Object val = expr.eval(symbols);

        boolean value;
        if (val instanceof Boolean) {
            value = (Boolean) val;
        } else {
            value = ((Integer) val) != 0; // trata int como booleano
        }

        return !value;
    }
}