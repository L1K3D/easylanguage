package com.easylanguage.runtime;

import java.util.HashMap;
import java.util.Map;

public class SymbolTable {
    private Map<String, Integer> table = new HashMap<>();

    public void declare(String name) {
        table.put(name, 0);
    }

    public void assign(String name, int value) {
        if (!table.containsKey(name)) {
            throw new RuntimeException("Variable not declared: " + name);
        }
        table.put(name, value);
    }

    public int get(String name) {
        if(!table.containsKey(name)) {
            throw new RuntimeException("Variable not declared: " + name);
        }
        return table.get(name);
    }
}