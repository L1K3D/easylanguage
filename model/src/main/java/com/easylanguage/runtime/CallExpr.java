package com.easylanguage.runtime;

import java.util.List;

public class CallExpr implements IExpr {
    private final String name;
    private final List<IExpr> args;

    public CallExpr(String name, List<IExpr> args) {
        this.name = name;
        this.args = args;
    }

    @Override
    public int eval(SymbolTable symbols) {
        FuncDecl func = symbols.getFunction(name);
        if (func == null) {
            throw new RuntimeException("Função não encontrada: " + name);
        }
        Object ret = func.invoke(symbols, args);
        String rt = func.getReturnType();
        if ("int".equals(rt)) {
            return (Integer) ret;
        } else if ("boolean".equals(rt)) {
            return ((Boolean) ret) ? 1 : 0;
        }
        throw new RuntimeException("Tipo de retorno de função não suportado: " + rt);
    }
}