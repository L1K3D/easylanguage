package com.easylanguage.runtime;

public class ReturnCommand implements ICommand {
    private final IExpr returnExpr;

    public ReturnCommand(IExpr returnExpr) {
        this.returnExpr = returnExpr;
    }

    @Override
    public void execute(SymbolTable symbols) {
        Object value = returnExpr.eval(symbols);
        throw new ReturnValue(value);
    }
}