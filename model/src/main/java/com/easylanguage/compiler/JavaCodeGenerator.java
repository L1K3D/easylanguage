package com.easylanguage.compiler;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import com.easylanguage.generated.EasyLanguageBaseListener;
import com.easylanguage.generated.EasyLanguageParser;


public class JavaCodeGenerator extends EasyLanguageBaseListener {
    private StringBuilder code;
    private int indentLevel;
    // track variables declared at top-level to avoid redeclaration in for-loops
    private java.util.Set<String> declaredVars = new java.util.HashSet<>();
    

    public JavaCodeGenerator() {
        this.code = new StringBuilder();
        this.indentLevel = 0;
        // current implementation does not need an explicit symbol table here
    }

    private void append(String text) {
        code.append("    ".repeat(indentLevel)).append(text);
    }

    private void appendLine(String line) {
        append(line + "\n");
    }

    public String generate(ParseTree tree) {
        // Cabeçalho da classe
        appendLine("public class GeneratedProgram {");
        indentLevel++;
        
        // Método principal
        appendLine("public static void main(String[] args) {");
        indentLevel++;

        // Percorre a árvore para gerar o código
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(this, tree);

        // Fecha os métodos e a classe
        indentLevel--;
        appendLine("}");
        indentLevel--;
        appendLine("}");

        return code.toString();
    }

    @Override
    public void enterDecl(EasyLanguageParser.DeclContext ctx) {
        String tipo = ctx.tipo().getText();
        String nome = ctx.ID().getText();
        
        // Mapeia tipos do EasyLanguage para Java
        String javaType = switch (tipo) {
            case "int" -> "int";
            case "boolean" -> "boolean";
            default -> tipo;
        };

        // Verifica se é um array (nota: token para numeros é NUMBER no parser gerado)
        if (ctx.NUMBER() != null) {
            appendLine(javaType + "[] " + nome + " = new " + javaType + "[" + ctx.NUMBER().getText() + "];" );
        } else {
            appendLine(javaType + " " + nome + ";");
        }
        // record declaration to avoid shadowing in for-loops
        declaredVars.add(nome);
    }

    @Override
    public void enterAssign(EasyLanguageParser.AssignContext ctx) {
        StringBuilder assignment = new StringBuilder();
        String target = ctx.ID().getText();
        
        // Se for atribuição em array
        if (ctx.expr().size() > 1) { // tem índice do array
            assignment.append(target)
                     .append("[")
                     .append(translateExpr(ctx.expr(0))) // índice
                     .append("] = ")
                     .append(translateExpr(ctx.expr(1))); // valor
        } else {
            assignment.append(target)
                     .append(" = ")
                     .append(translateExpr(ctx.expr(0)));
        }
        
        appendLine(assignment.append(";").toString());
    }

    @Override
    public void enterFuncDecl(EasyLanguageParser.FuncDeclContext ctx) {
        String nome = ctx.ID().getText();
        String tipoRetorno = ctx.tipo().getText();
        
        StringBuilder funcDecl = new StringBuilder("public static ");
        funcDecl.append(tipoRetorno)
               .append(" ")
               .append(nome)
               .append("(");
        
        // Parâmetros
        if (ctx.paramList() != null) {
            for (int i = 0; i < ctx.paramList().param().size(); i++) {
                var param = ctx.paramList().param(i);
                if (i > 0) funcDecl.append(", ");
                funcDecl.append(param.tipo().getText())
                       .append(" ")
                       .append(param.ID().getText());
            }
        }
        
        funcDecl.append(") {");
        appendLine(funcDecl.toString());
        indentLevel++;
    }

    @Override
    public void exitFuncDecl(EasyLanguageParser.FuncDeclContext ctx) {
        indentLevel--;
        appendLine("}");
    }

    private String translateExpr(EasyLanguageParser.ExprContext ctx) {
        // The parser generates many specific ExprContext subclasses. Check them in order.
        if (ctx instanceof EasyLanguageParser.NumberExprContext) {
            return ((EasyLanguageParser.NumberExprContext) ctx).NUMBER().getText();
        }
        if (ctx instanceof EasyLanguageParser.TrueExprContext) {
            return "true"; // VERDADEIRO
        }
        if (ctx instanceof EasyLanguageParser.FalseExprContext) {
            return "false"; // FALSO
        }
        if (ctx instanceof EasyLanguageParser.VarExprContext) {
            return ((EasyLanguageParser.VarExprContext) ctx).ID().getText();
        }
        if (ctx instanceof EasyLanguageParser.ArrayAccessExprContext) {
            EasyLanguageParser.ArrayAccessExprContext a = (EasyLanguageParser.ArrayAccessExprContext) ctx;
            return a.ID().getText() + "[" + translateExpr(a.expr()) + "]";
        }
        if (ctx instanceof EasyLanguageParser.CallExprContext) {
            EasyLanguageParser.CallExprContext c = (EasyLanguageParser.CallExprContext) ctx;
            EasyLanguageParser.CallContext call = c.call();
            StringBuilder sb = new StringBuilder();
            sb.append(call.ID().getText()).append("(");
            if (call.argList() != null) {
                var args = call.argList().expr();
                for (int i = 0; i < args.size(); i++) {
                    if (i > 0) sb.append(", ");
                    sb.append(translateExpr(args.get(i)));
                }
            }
            sb.append(")");
            return sb.toString();
        }
        if (ctx instanceof EasyLanguageParser.ParenExprContext) {
            return "(" + translateExpr(((EasyLanguageParser.ParenExprContext) ctx).expr()) + ")";
        }
        if (ctx instanceof EasyLanguageParser.NotExprContext) {
            return "!(" + translateExpr(((EasyLanguageParser.NotExprContext) ctx).expr()) + ")";
        }

        // Binary/recursive expression contexts (add, sub, mul, div, comparisons, logical ops)
        if (ctx instanceof EasyLanguageParser.AddExprContext) {
            var e = (EasyLanguageParser.AddExprContext) ctx;
            return "(" + translateExpr(e.expr(0)) + " + " + translateExpr(e.expr(1)) + ")";
        }
        if (ctx instanceof EasyLanguageParser.SubExprContext) {
            var e = (EasyLanguageParser.SubExprContext) ctx;
            return "(" + translateExpr(e.expr(0)) + " - " + translateExpr(e.expr(1)) + ")";
        }
        if (ctx instanceof EasyLanguageParser.MulExprContext) {
            var e = (EasyLanguageParser.MulExprContext) ctx;
            return "(" + translateExpr(e.expr(0)) + " * " + translateExpr(e.expr(1)) + ")";
        }
        if (ctx instanceof EasyLanguageParser.DivExprContext) {
            var e = (EasyLanguageParser.DivExprContext) ctx;
            return "(" + translateExpr(e.expr(0)) + " / " + translateExpr(e.expr(1)) + ")";
        }
        if (ctx instanceof EasyLanguageParser.LtExprContext) {
            var e = (EasyLanguageParser.LtExprContext) ctx;
            return "(" + translateExpr(e.expr(0)) + " < " + translateExpr(e.expr(1)) + ")";
        }
        if (ctx instanceof EasyLanguageParser.GtExprContext) {
            var e = (EasyLanguageParser.GtExprContext) ctx;
            return "(" + translateExpr(e.expr(0)) + " > " + translateExpr(e.expr(1)) + ")";
        }
        if (ctx instanceof EasyLanguageParser.EqExprContext) {
            var e = (EasyLanguageParser.EqExprContext) ctx;
            return "(" + translateExpr(e.expr(0)) + " == " + translateExpr(e.expr(1)) + ")";
        }
        if (ctx instanceof EasyLanguageParser.NeqExprContext) {
            var e = (EasyLanguageParser.NeqExprContext) ctx;
            return "(" + translateExpr(e.expr(0)) + " != " + translateExpr(e.expr(1)) + ")";
        }
        if (ctx instanceof EasyLanguageParser.AndExprContext) {
            var e = (EasyLanguageParser.AndExprContext) ctx;
            return "(" + translateExpr(e.expr(0)) + " && " + translateExpr(e.expr(1)) + ")";
        }
        if (ctx instanceof EasyLanguageParser.OrExprContext) {
            var e = (EasyLanguageParser.OrExprContext) ctx;
            return "(" + translateExpr(e.expr(0)) + " || " + translateExpr(e.expr(1)) + ")";
        }

        // Fallback: use the text representation
        return ctx.getText();
    }

    @Override
    public void enterWrite(EasyLanguageParser.WriteContext ctx) {
        appendLine("System.out.println(" + translateExpr(ctx.expr()) + ");");
    }

    @Override
    public void enterEnquantoCmd(EasyLanguageParser.EnquantoCmdContext ctx) {
        appendLine("while (" + translateExpr(ctx.expr()) + ") {");
        indentLevel++;
    }

    @Override
    public void exitEnquantoCmd(EasyLanguageParser.EnquantoCmdContext ctx) {
        indentLevel--;
        appendLine("}");
    }

    @Override
    public void enterParaCmd(EasyLanguageParser.ParaCmdContext ctx) {
        String var = ctx.ID().getText();
        String inicio = translateExpr(ctx.expr(0));
        String fim = translateExpr(ctx.expr(1));
        String passo = ctx.expr().size() > 2 ? translateExpr(ctx.expr(2)) : "1";
        
        // if the loop variable was already declared earlier, don't redeclare
        if (declaredVars.contains(var)) {
            appendLine("for (" + var + " = " + inicio + "; " + 
                      var + " <= " + fim + "; " + 
                      var + " += " + passo + ") {");
        } else {
            appendLine("for (int " + var + " = " + inicio + "; " + 
                      var + " <= " + fim + "; " + 
                      var + " += " + passo + ") {");
        }
        indentLevel++;
    }

    @Override
    public void exitParaCmd(EasyLanguageParser.ParaCmdContext ctx) {
        indentLevel--;
        appendLine("}");
    }

    @Override
    public void enterReturnStmt(EasyLanguageParser.ReturnStmtContext ctx) {
        appendLine("return " + translateExpr(ctx.expr()) + ";");
    }
}