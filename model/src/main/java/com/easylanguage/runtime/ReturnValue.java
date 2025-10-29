package com.easylanguage.runtime;

public class ReturnValue extends RuntimeException {
    public final Object value;
    public ReturnValue(Object value) {
        super(null, null, false, false); // sem stack trace para ser leve
        this.value = value;
    }
}