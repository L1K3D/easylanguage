package com.easylanguage.ast;

import com.easylanguage.generated.EasyLanguageBaseVisitor;
import com.easylanguage.generated.EasyLanguageParser;
import java.util.*;

public class AstBuilder extends EasyLanguageBaseVisitor<Object> {
    @Override
    public Object visitProgram(EasyLanguageParser.ProgramContext ctx) {
        ProgramNode p = new ProgramNode();
        for (var decl : ctx.decl()) {
            p.add((ASTNode)visitDecl(decl));
        }
        for (var func : ctx.funcDecl()) {
            p.add((ASTNode)visitFuncDecl(func));
        }
        for (var proc : ctx.procDecl()) {
            p.add((ASTNode)visitProcDecl(proc));
        }
        for (var cmd : ctx.command()) {
            p.add((ASTNode)visitCommand(cmd));
        }
        return p;
    }

    @Override
    public Object visitDecl(EasyLanguageParser.DeclContext ctx) {
        String tipo = ctx.tipo().getText();
        String name = ctx.ID().getText();
        if (ctx.NUMBER() != null) {
            int size = Integer.parseInt(ctx.NUMBER().getText());
            return new VarDecl(tipo, name, size);
        }
        return new VarDecl(tipo, name, null);
    }

    @Override
    public Object visitFuncDecl(EasyLanguageParser.FuncDeclContext ctx) {
        String name = ctx.ID().getText();
        String retType = ctx.tipo().getText();
        List<VarDecl> params = new ArrayList<>();
        if (ctx.paramList() != null) {
            for (var p : ctx.paramList().param()) {
                params.add(new VarDecl(p.tipo().getText(), p.ID().getText(), null));
            }
        }
    List<ASTNode> body = new ArrayList<>();
    for (var d : ctx.decl()) body.add((ASTNode)visitDecl(d));
        for (var c : ctx.command()) body.add((ASTNode)visitCommand(c));
        return new FuncDeclNode(name, params, retType, body);
    }

    @Override
    public Object visitProcDecl(EasyLanguageParser.ProcDeclContext ctx) {
        String name = ctx.ID().getText();
        List<VarDecl> params = new ArrayList<>();
        if (ctx.paramList() != null) {
            for (var p : ctx.paramList().param()) params.add(new VarDecl(p.tipo().getText(), p.ID().getText(), null));
        }
    List<ASTNode> body = new ArrayList<>();
    for (var d : ctx.decl()) body.add((ASTNode)visitDecl(d));
        for (var c : ctx.command()) body.add((ASTNode)visitCommand(c));
        return new ProcDeclNode(name, params, body);
    }

    @Override
    public Object visitCommand(EasyLanguageParser.CommandContext ctx) {
        if (ctx.assign() != null) return visitAssign(ctx.assign());
        if (ctx.write() != null) return visitWrite(ctx.write());
        if (ctx.enquantoCmd() != null) return visitEnquantoCmd(ctx.enquantoCmd());
        if (ctx.paraCmd() != null) return visitParaCmd(ctx.paraCmd());
        if (ctx.call() != null) {
            // treat call as statement (call then ;)
            var call = visitCall(ctx.call());
            if (call instanceof CallExpr) return new WriteStmt((Expr)call); // fallback: print the call result
            return null;
        }
        if (ctx.returnStmt() != null) return visitReturnStmt(ctx.returnStmt());
        return null;
    }

    @Override
    public Object visitAssign(EasyLanguageParser.AssignContext ctx) {
        if (ctx.ABRECOL() != null) {
            // array assign: ID '[' expr ']' '=' expr
            String name = ctx.ID().getText();
            Expr index = (Expr)visit(ctx.expr(0));
            Expr value = (Expr)visit(ctx.expr(1));
            ArrayAccessExpr target = new ArrayAccessExpr(name, index);
            return new AssignStmt(target, value);
        } else {
            Expr target = new VarExpr(ctx.ID().getText());
            Expr value = (Expr)visit(ctx.expr(0));
            return new AssignStmt(target, value);
        }
    }

    @Override
    public Object visitWrite(EasyLanguageParser.WriteContext ctx) {
        Expr e = (Expr)visit(ctx.expr());
        return new WriteStmt(e);
    }

    @Override
    public Object visitEnquantoCmd(EasyLanguageParser.EnquantoCmdContext ctx) {
        Expr cond = (Expr)visit(ctx.expr());
        List<Stmt> body = new ArrayList<>();
        for (var c : ctx.command()) body.add((Stmt)visitCommand(c));
        return new WhileStmt(cond, body);
    }

    @Override
    public Object visitParaCmd(EasyLanguageParser.ParaCmdContext ctx) {
        String var = ctx.ID().getText();
        Expr start = (Expr)visit(ctx.expr(0));
        Expr end = (Expr)visit(ctx.expr(1));
        Expr step = (ctx.PASSO() != null) ? (Expr)visit(ctx.expr(2)) : new NumberExpr(1);
        List<Stmt> body = new ArrayList<>();
        for (var c : ctx.command()) body.add((Stmt)visitCommand(c));
        return new ForStmt(var, start, end, step, body);
    }

    @Override
    public Object visitCall(EasyLanguageParser.CallContext ctx) {
        String name = ctx.ID().getText();
        List<Expr> args = new ArrayList<>();
        if (ctx.argList() != null) {
            for (var e : ctx.argList().expr()) args.add((Expr)visit(e));
        }
        return new CallExpr(name, args);
    }

    @Override
    public Object visitReturnStmt(EasyLanguageParser.ReturnStmtContext ctx) {
        Expr e = (Expr)visit(ctx.expr());
        return new ReturnStmt(e);
    }

    @Override
    public Object visitNumberExpr(EasyLanguageParser.NumberExprContext ctx) {
        int v = Integer.parseInt(ctx.NUMBER().getText());
        return new NumberExpr(v);
    }

    @Override
    public Object visitTrueExpr(EasyLanguageParser.TrueExprContext ctx) {
        return new BoolExpr(true);
    }

    @Override
    public Object visitFalseExpr(EasyLanguageParser.FalseExprContext ctx) {
        return new BoolExpr(false);
    }

    @Override
    public Object visitVarExpr(EasyLanguageParser.VarExprContext ctx) {
        return new VarExpr(ctx.ID().getText());
    }

    @Override
    public Object visitArrayAccessExpr(EasyLanguageParser.ArrayAccessExprContext ctx) {
        return new ArrayAccessExpr(ctx.ID().getText(), (Expr)visit(ctx.expr()));
    }

    @Override
    public Object visitParenExpr(EasyLanguageParser.ParenExprContext ctx) {
        return visit(ctx.expr());
    }

    @Override
    public Object visitNotExpr(EasyLanguageParser.NotExprContext ctx) {
        return new NotExpr((Expr)visit(ctx.expr()));
    }

    @Override
    public Object visitAddExpr(EasyLanguageParser.AddExprContext ctx) {
        return new BinaryExpr("+", (Expr)visit(ctx.expr(0)), (Expr)visit(ctx.expr(1)));
    }

    @Override
    public Object visitSubExpr(EasyLanguageParser.SubExprContext ctx) {
        return new BinaryExpr("-", (Expr)visit(ctx.expr(0)), (Expr)visit(ctx.expr(1)));
    }

    @Override
    public Object visitMulExpr(EasyLanguageParser.MulExprContext ctx) {
        return new BinaryExpr("*", (Expr)visit(ctx.expr(0)), (Expr)visit(ctx.expr(1)));
    }

    @Override
    public Object visitDivExpr(EasyLanguageParser.DivExprContext ctx) {
        return new BinaryExpr("/", (Expr)visit(ctx.expr(0)), (Expr)visit(ctx.expr(1)));
    }

    @Override
    public Object visitLtExpr(EasyLanguageParser.LtExprContext ctx) {
        return new BinaryExpr("<", (Expr)visit(ctx.expr(0)), (Expr)visit(ctx.expr(1)));
    }

    @Override
    public Object visitGtExpr(EasyLanguageParser.GtExprContext ctx) {
        return new BinaryExpr(">", (Expr)visit(ctx.expr(0)), (Expr)visit(ctx.expr(1)));
    }

    @Override
    public Object visitEqExpr(EasyLanguageParser.EqExprContext ctx) {
        return new BinaryExpr("==", (Expr)visit(ctx.expr(0)), (Expr)visit(ctx.expr(1)));
    }

    @Override
    public Object visitNeqExpr(EasyLanguageParser.NeqExprContext ctx) {
        return new BinaryExpr("!=", (Expr)visit(ctx.expr(0)), (Expr)visit(ctx.expr(1)));
    }

    @Override
    public Object visitAndExpr(EasyLanguageParser.AndExprContext ctx) {
        return new BinaryExpr("&&", (Expr)visit(ctx.expr(0)), (Expr)visit(ctx.expr(1)));
    }

    @Override
    public Object visitOrExpr(EasyLanguageParser.OrExprContext ctx) {
        return new BinaryExpr("||", (Expr)visit(ctx.expr(0)), (Expr)visit(ctx.expr(1)));
    }

    @Override
    public Object visitCallExpr(EasyLanguageParser.CallExprContext ctx) {
        return visitCall(ctx.call());
    }
}
