package com.easylanguage.runtime;

public class RelationalExpr implements IExpr {
    private String operator;
    private IExpr left, right;
    
    public RelationalExpr(String operator, IExpr left, IExpr right) {
        this.operator = operator;
        this.left = left;
        this.right = right;
    }

    // Construtor com ordem (left, op, right) para compatibilidade com exemplos
    public RelationalExpr(IExpr left, String op, IExpr right) {
        this(op, left, right);
    }
    
    @Override
    public int eval(SymbolTable symbols) {
        int leftValue = left.eval(symbols);
        int rightValue = right.eval(symbols);
        
        switch (operator) {
            case "<":
                return leftValue < rightValue ? 1 : 0;
            case "<=":
                return leftValue <= rightValue ? 1 : 0;
            case ">":
                return leftValue > rightValue ? 1 : 0;
            case ">=":
                return leftValue >= rightValue ? 1 : 0;
            case "==":
                return leftValue == rightValue ? 1 : 0;
            case "!=":
                return leftValue != rightValue ? 1 : 0;
            default:
                throw new RuntimeException("Operador relacional não suportado: " + operator);
        }
    }
}