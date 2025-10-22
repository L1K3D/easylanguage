package com.easylanguage.runtime;

import java.util.List;

public class CommandEnquanto implements ICommand {
    private IExpr condition;
    private List<ICommand> body;

    public CommandEnquanto(IExpr condition, List<ICommand> body) {
        this.condition = condition;
        this.body = body;
    }

    @Override
    public void execute(SymbolTable symbols) {
        while (condition.evalAsBool(symbols)) {
            for (ICommand cmd : body) {
                cmd.execute(symbols);
            }
        }
    }
}