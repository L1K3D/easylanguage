package com.easylanguage.runtime;

public class ArrayAccessExpr implements IExpr {
    private String arrayName;
    private IExpr indexExpr;
    
    public ArrayAccessExpr(String arrayName, IExpr indexExpr) {
        this.arrayName = arrayName;
        this.indexExpr = indexExpr;
    }
    
    @Override
    public int eval(SymbolTable symbols) {
        Object array = symbols.get(arrayName);
        int index = indexExpr.evalAsInt(symbols);

        if (array instanceof int[]) {
            return ((int[])array)[index];
        } else if (array instanceof boolean[]) {
            return ((boolean[])array)[index] ? 1 : 0;
        }
        throw new RuntimeException("Tipo de array não suportado: " + array.getClass());
    }
}