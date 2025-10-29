package com.easylanguage.runtime;

import java.util.List;

public class ProcDecl {
    public static class Param {
        public final String type;
        public final String name;
        public Param(String type, String name) { this.type = type; this.name = name; }
    }

    private final String name;
    private final List<Param> params;
    private final List<ICommand> body;

    public ProcDecl(String name, List<Param> params, List<ICommand> body) {
        this.name = name;
        this.params = params;
        this.body = body;
    }

    public String getName() { return name; }
    public List<Param> getParams() { return params; }

    public void setBody(List<ICommand> body) {
        this.body.clear();
        this.body.addAll(body);
    }

    public void invoke(SymbolTable symbols, List<IExpr> args) {
        if (args.size() != params.size()) {
            throw new RuntimeException("Quantidade de argumentos inválida para procedimento: " + name);
        }

        SymbolTable local = symbols.pushScope();
        try {
            for (int i = 0; i < params.size(); i++) {
                Object value = args.get(i).eval(symbols);
                Param p = params.get(i);
                if (p.type.equals("int")) {
                    local.declareInt(p.name);
                    local.assign(p.name, (Integer) coerceToInt(value));
                } else if (p.type.equals("boolean")) {
                    local.declareBool(p.name);
                    local.assign(p.name, (Boolean) coerceToBool(value));
                } else {
                    throw new RuntimeException("Tipo de parâmetro não suportado: " + p.type);
                }
            }

            for (ICommand cmd : body) {
                cmd.execute(local);
            }
        } finally {
            symbols.popScope();
        }
    }

    private Integer coerceToInt(Object v) {
        if (v instanceof Integer) return (Integer) v;
        if (v instanceof Boolean) return ((Boolean) v) ? 1 : 0;
        throw new RuntimeException("Valor não pode ser convertido para int: " + v);
    }

    private Boolean coerceToBool(Object v) {
        if (v instanceof Boolean) return (Boolean) v;
        if (v instanceof Integer) return ((Integer) v) != 0;
        throw new RuntimeException("Valor não pode ser convertido para boolean: " + v);
    }
}