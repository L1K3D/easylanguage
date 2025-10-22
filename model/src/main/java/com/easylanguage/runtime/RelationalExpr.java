package com.easylanguage.runtime;

public class RelationalExpr implements IExpr {
    private String operator;
    private IExpr left, right;
    
    public RelationalExpr(String operator, IExpr left, IExpr right) {
        this.operator = operator;
        this.left = left;
        this.right = right;
    }
    
    @Override
    public Object eval(SymbolTable symbols) {
        int leftValue = left.evalAsInt(symbols);
        int rightValue = right.evalAsInt(symbols);
        
        switch (operator) {
            case "<":
                return leftValue < rightValue;
            case "<=":
                return leftValue <= rightValue;
            case ">":
                return leftValue > rightValue;
            case ">=":
                return leftValue >= rightValue;
            case "==":
                return leftValue == rightValue;
            case "!=":
                return leftValue != rightValue;
            default:
                throw new RuntimeException("Operador relacional não suportado: " + operator);
        }
    }
}