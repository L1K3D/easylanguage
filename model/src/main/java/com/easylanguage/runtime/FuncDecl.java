package com.easylanguage.runtime;

import java.util.List;

public class FuncDecl {
    public static class Param {
        public final String type; // "int" ou "boolean"
        public final String name;
        public Param(String type, String name) { this.type = type; this.name = name; }
    }

    private final String name;
    private final String returnType; // "int" ou "boolean"
    private final List<Param> params;
    private final List<ICommand> body;

    public FuncDecl(String name, String returnType, List<Param> params, List<ICommand> body) {
        this.name = name;
        this.returnType = returnType;
        this.params = params;
        this.body = body;
    }

    public String getName() { return name; }
    public String getReturnType() { return returnType; }
    public List<Param> getParams() { return params; }

    public void setBody(List<ICommand> body) {
        this.body.clear();
        this.body.addAll(body);
    }

    public Object invoke(SymbolTable symbols, List<IExpr> args) {
        if (args.size() != params.size()) {
            throw new RuntimeException("Quantidade de argumentos inválida para função: " + name);
        }

        symbols.pushScope();
        try {
            for (int i = 0; i < params.size(); i++) {
                Object value = args.get(i).eval(symbols);
                Param p = params.get(i);
                // Declaração local do parâmetro
                if (p.type.equals("int")) {
                    symbols.declareInt(p.name);
                    symbols.assign(p.name, coerceToInt(value));
                } else if (p.type.equals("boolean")) {
                    symbols.declareBool(p.name);
                    symbols.assign(p.name, coerceToBool(value) ? 1 : 0);
                } else {
                    throw new RuntimeException("Tipo de parâmetro não suportado: " + p.type);
                }
            }

            // Executa corpo. ReturnValue quebra o fluxo e carrega o valor.
            for (ICommand cmd : body) {
                cmd.execute(symbols);
            }

            // Se nenhuma sentença return foi executada:
            throw new RuntimeException("Função '" + name + "' terminou sem return.");
        } catch (ReturnValue rv) {
            Object ret = rv.value;
            // Checagem simples de tipo no retorno
            if (returnType.equals("int")) {
                return coerceToInt(ret);
            } else if (returnType.equals("boolean")) {
                return coerceToBool(ret);
            } else {
                throw new RuntimeException("Tipo de retorno não suportado: " + returnType);
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