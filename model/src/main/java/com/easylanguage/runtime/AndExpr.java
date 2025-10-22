package com.easylanguage.runtime;

public class AndExpr implements IExpr {
    private IExpr left, right;
    
    public AndExpr(IExpr left, IExpr right) { 
        this.left = left; 
        this.right = right; 
    }
    
    @Override
    public Object eval(SymbolTable symbols) {
        boolean leftValue = left.evalAsBool(symbols);
        boolean rightValue = right.evalAsBool(symbols);
        return leftValue && rightValue;
    }
}