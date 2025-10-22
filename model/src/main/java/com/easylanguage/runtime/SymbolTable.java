package com.easylanguage.runtime;

import java.util.HashMap;
import java.util.Map;

public class SymbolTable {
    private Map<String, Object> table = new HashMap<>();

    // Declaração de variáveis escalares
    public void declareInt(String name) {
        table.put(name, 0);
    }

    public void declareBoolean(String name) {
        table.put(name, Boolean.FALSE);
    }

    // Declaração de arrays
    public void declareIntArray(String name, int size) {
        table.put(name, new int[size]);
    }

    public void declareBooleanArray(String name, int size) {
        table.put(name, new boolean[size]);
    }

    // Atribuição de valores escalares
    public void assign(String name, Object value) {
        if (!table.containsKey(name)) {
            throw new RuntimeException("Variável não declarada: " + name);
        }
        table.put(name, value);
    }

    // Atribuição em arrays
    public void assignArrayElement(String name, int index, Object value) {
        if (!table.containsKey(name)) {
            throw new RuntimeException("Array não declarado: " + name);
        }
        Object arr = table.get(name);
        if (arr instanceof int[]) {
            ((int[]) arr)[index] = (Integer) value;
        } else if (arr instanceof boolean[]) {
            ((boolean[]) arr)[index] = (Boolean) value;
        } else {
            throw new RuntimeException("Símbolo não é um array: " + name);
        }
    }

    // Recuperar valores escalares
    public Object get(String name) {
        if (!table.containsKey(name)) {
            throw new RuntimeException("Variável não declarada: " + name);
        }
        return table.get(name);
    }

    // Recuperar elementos de arrays
    public Object getArrayElement(String name, int index) {
        if (!table.containsKey(name)) {
            throw new RuntimeException("Array não declarado: " + name);
        }
        Object arr = table.get(name);
        if (arr instanceof int[]) {
            return ((int[]) arr)[index];
        } else if (arr instanceof boolean[]) {
            return ((boolean[]) arr)[index];
        }
        throw new RuntimeException("Símbolo não é um array: " + name);
    }
}