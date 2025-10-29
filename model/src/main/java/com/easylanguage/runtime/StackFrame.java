package com.easylanguage.runtime;

import java.util.HashMap;
import java.util.Map;

public class StackFrame {
    private String name;
    private Map<String, Object> locals = new HashMap<>();
    
    public StackFrame(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    public void setLocal(String name, Object value) {
        locals.put(name, value);
    }
    
    public Object getLocal(String name) {
        return locals.get(name);
    }
    
    public boolean hasLocal(String name) {
        return locals.containsKey(name);
    }
}