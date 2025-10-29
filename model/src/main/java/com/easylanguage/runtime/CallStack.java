package com.easylanguage.runtime;

import java.util.Stack;

public class CallStack {
    private Stack<StackFrame> frames = new Stack<>();
    
    public void pushFrame(String name) {
        frames.push(new StackFrame(name));
    }
    
    public void popFrame() {
        frames.pop();
    }
    
    public StackFrame currentFrame() {
        if (frames.isEmpty()) {
            return null;
        }
        return frames.peek();
    }

    public boolean isEmpty() {
        return frames.isEmpty();
    }
}