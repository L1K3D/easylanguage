package com.easylanguage.runtime;

import java.util.List;

public class CommandPara implements ICommand {
    private String var;
    private IExpr start;
    private IExpr end;
    private IExpr step;
    private List<ICommand> body;

    public CommandPara(String var, IExpr start, IExpr end, IExpr step, List<ICommand> body) {
        this.var = var;
        this.start = start;
        this.end = end;
        this.step = (step != null) ? step : new IExpr() {
            @Override
            public int eval(SymbolTable symbols) {
                return 1;
            }
        };
        this.body = body;
    }

    @Override
    public void execute(SymbolTable symbols) {
        int i = start.evalAsInt(symbols);
        int limite = end.evalAsInt(symbols);
        int passo = step.evalAsInt(symbols);

        for (; i <= limite; i += passo) {
            symbols.assign(var, i);
            for (ICommand cmd : body) {
                cmd.execute(symbols);
            }
        }
    }
}