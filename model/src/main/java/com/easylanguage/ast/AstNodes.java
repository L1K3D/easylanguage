package com.easylanguage.ast;

import java.util.*;

interface ASTNode { }

// Program node: list of declarations and commands
class ProgramNode implements ASTNode {
    public final List<ASTNode> items = new ArrayList<>();
    public void add(ASTNode n) { items.add(n); }
    @Override public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Program\n");
        for (ASTNode n : items) sb.append(n.toString()).append('\n');
        return sb.toString();
    }
}

// Declarations
class VarDecl implements ASTNode {
    public final String type; // "int" or "boolean" or "int[]" etc
    public final String name;
    public final Integer size; // null for non-array
    public VarDecl(String type, String name, Integer size) { this.type=type; this.name=name; this.size=size; }
    @Override public String toString() { return "VarDecl("+type+" " + name + (size!=null?"["+size+"]":"") + ")"; }
}

// Statements
abstract class Stmt implements ASTNode { }

class AssignStmt extends Stmt {
    public final Expr target; // VarExpr or ArrayAccess
    public final Expr value;
    public AssignStmt(Expr target, Expr value) { this.target = target; this.value = value; }
    @Override public String toString() { return "Assign("+target+" = "+value+")"; }
}

class WriteStmt extends Stmt {
    public final Expr expr;
    public WriteStmt(Expr e){ this.expr = e; }
    @Override public String toString(){ return "Write("+expr+")"; }
}

class ForStmt extends Stmt {
    public final String var;
    public final Expr start, end, step;
    public final List<Stmt> body;
    public ForStmt(String var, Expr start, Expr end, Expr step, List<Stmt> body){ this.var=var;this.start=start;this.end=end;this.step=step;this.body=body; }
    @Override public String toString(){ return "For("+var+" from "+start+" to "+end+" step "+step+" body="+body+")"; }
}

class WhileStmt extends Stmt {
    public final Expr cond; public final List<Stmt> body;
    public WhileStmt(Expr cond, List<Stmt> body){ this.cond=cond;this.body=body; }
    @Override public String toString(){ return "While("+cond+" body="+body+")"; }
}

// Expressions
abstract class Expr implements ASTNode { }

class NumberExpr extends Expr {
    public final int value; public NumberExpr(int v){ this.value=v; }
    @Override public String toString(){ return String.valueOf(value); }
}

class BoolExpr extends Expr {
    public final boolean value; public BoolExpr(boolean v){ this.value=v; }
    @Override public String toString(){ return String.valueOf(value); }
}

class VarExpr extends Expr {
    public final String name; public VarExpr(String n){ this.name=n; }
    @Override public String toString(){ return name; }
}

class ArrayAccessExpr extends Expr {
    public final String arrayName; public final Expr index;
    public ArrayAccessExpr(String arrayName, Expr index){ this.arrayName=arrayName; this.index=index; }
    @Override public String toString(){ return arrayName+"["+index+"]"; }
}

class BinaryExpr extends Expr {
    public final String op; public Expr left, right;
    public BinaryExpr(String op, Expr l, Expr r){ this.op=op; this.left=l; this.right=r; }
    @Override public String toString(){ return "("+left+" "+op+" "+right+")"; }
}

class NotExpr extends Expr {
    public Expr inner; public NotExpr(Expr e){ this.inner=e; }
    @Override public String toString(){ return "(not "+inner+")"; }
}

class CallExpr extends Expr {
    public final String name; public final List<Expr> args;
    public CallExpr(String name, List<Expr> args){ this.name=name; this.args=args; }
    @Override public String toString(){ return name+args; }
}

// Return, FuncDecl, ProcDecl minimal nodes (for completeness)
class ReturnStmt extends Stmt {
    public final Expr expr; public ReturnStmt(Expr expr){ this.expr=expr; }
    @Override public String toString(){ return "Return("+expr+")"; }
}

class FuncDeclNode implements ASTNode {
    public final String name; public final List<VarDecl> params; public final String returnType; public final List<ASTNode> body;
    public FuncDeclNode(String name, List<VarDecl> params, String returnType, List<ASTNode> body){ this.name=name; this.params=params; this.returnType=returnType; this.body=body; }
    @Override public String toString(){ return "Func("+name+" params="+params+" returns="+returnType+" body="+body+")"; }
}

class ProcDeclNode implements ASTNode {
    public final String name; public final List<VarDecl> params; public final List<ASTNode> body;
    public ProcDeclNode(String name, List<VarDecl> params, List<ASTNode> body){ this.name=name; this.params=params; this.body=body; }
    @Override public String toString(){ return "Proc("+name+" params="+params+" body="+body+")"; }
}
