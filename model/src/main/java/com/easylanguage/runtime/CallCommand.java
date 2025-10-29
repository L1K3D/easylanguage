package com.easylanguage.runtime;

import java.util.List;

public class CallCommand implements ICommand {
    private final String name;
    private final List<IExpr> args;

    public CallCommand(String name, List<IExpr> args) {
        this.name = name;
        this.args = args;
    }

    @Override
    public void execute(SymbolTable symbols) {
        // Primeiro tenta procedimento; se não existir, tenta função e ignora retorno
        ProcDecl proc = symbols.getProcedure(name);
        if (proc != null) {
            proc.invoke(symbols, args);
            return;
        }
        FuncDecl func = symbols.getFunction(name);
        if (func != null) {
            func.invoke(symbols, args); // retorno é descartado
            return;
        }
        throw new RuntimeException("Procedimento/Função não encontrado: " + name);
    }
}