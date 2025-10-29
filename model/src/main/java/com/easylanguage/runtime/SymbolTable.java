package com.easylanguage.runtime;

import java.util.*;

public class SymbolTable {
    private final Deque<Map<String, Object>> scopes = new ArrayDeque<>();
    // parallel structure to store variable types per scope
    private final Deque<Map<String, String>> typeScopes = new ArrayDeque<>();
    private final Map<String, FuncDecl> functions = new HashMap<>();
    private final Map<String, ProcDecl> procedures = new HashMap<>();
    private CallStack callStack = new CallStack();

    public SymbolTable() {
        // escopo global
        scopes.push(new HashMap<>());
        typeScopes.push(new HashMap<>());
    }

    // Escopos
    public SymbolTable pushScope() {
        scopes.push(new HashMap<>());
        typeScopes.push(new HashMap<>());
        return this;
    }

    public void popScope() {
        if (scopes.size() <= 1) {
            throw new RuntimeException("Não é possível remover o escopo global.");
        }
        scopes.pop();
        typeScopes.pop();
    }

    // Declarações
    public void declareInt(String name) {
        if (current().containsKey(name)) throw new RuntimeException("Nome já declarado no escopo atual: " + name);
        current().put(name, 0);
        typeScopes.peek().put(name, "int");
    }

    public void declareBool(String name) {
        if (current().containsKey(name)) throw new RuntimeException("Nome já declarado no escopo atual: " + name);
        current().put(name, false);
        typeScopes.peek().put(name, "boolean");
    }

    public void declareIntArray(String name, int size) {
        if (current().containsKey(name)) throw new RuntimeException("Nome já declarado no escopo atual: " + name);
        int[] arr = new int[size];
        current().put(name, arr);
        typeScopes.peek().put(name, "int[]");
    }

    public void declareBoolArray(String name, int size) {
        if (current().containsKey(name)) throw new RuntimeException("Nome já declarado no escopo atual: " + name);
        boolean[] arr = new boolean[size];
        current().put(name, arr);
        typeScopes.peek().put(name, "boolean[]");
    }

    // Atribuição e leitura
    public void assign(String name, Object value) {
        Map<String, Object> scope = findScope(name);
        if (scope == null) throw new RuntimeException("Variável não declarada: " + name);
        scope.put(name, value);
    }

    public Object get(String name) {
        // Primeiro procura na pilha de chamadas
        if (callStack != null && !callStack.isEmpty() && callStack.currentFrame().hasLocal(name)) {
            return callStack.currentFrame().getLocal(name);
        }
        // Se não encontrar, procura nos escopos
        Map<String, Object> scope = findScope(name);
        if (scope == null) throw new RuntimeException("Variável não declarada: " + name);
        return scope.get(name);
    }

    public String getType(String name) {
        // First check call stack locals — currently we don't store types in CallStack/StackFrame,
        // so fallback to lexical scopes
        for (Map<String, String> ts : typeScopes) {
            if (ts.containsKey(name)) return ts.get(name);
        }
        return null;
    }

    public void set(String name, Object value) {
        // Se a variável existir no frame atual, atualiza lá
        if (callStack != null && !callStack.isEmpty() && callStack.currentFrame().hasLocal(name)) {
            callStack.currentFrame().setLocal(name, value);
            return;
        }
        // Caso contrário, tenta atualizar no escopo apropriado
        Map<String, Object> scope = findScope(name);
        if (scope == null) throw new RuntimeException("Variável não declarada: " + name);
        scope.put(name, value);
    }

    private Map<String, Object> findScope(String name) {
        for (Map<String, Object> scope : scopes) {
            if (scope.containsKey(name)) return scope;
        }
        return null;
    }

    private Map<String, Object> current() {
        return scopes.peek();
    }

    // Funções e procedimentos
    public void registerFunction(FuncDecl func) {
        functions.put(func.getName(), func);
    }

    public void registerProcedure(ProcDecl proc) {
        procedures.put(proc.getName(), proc);
    }

    public FuncDecl getFunction(String name) {
        return functions.get(name);
    }

    public ProcDecl getProcedure(String name) {
        return procedures.get(name);
    }
}