package com.easylanguage.runtime;

import java.util.List;
import java.util.ArrayList;

import com.easylanguage.generated.EasyLanguageBaseListener;
import com.easylanguage.generated.EasyLanguageParser;

public class Interpreter extends EasyLanguageBaseListener {
    private SymbolTable symbols = new SymbolTable();

    @Override
    public void exitDecl(EasyLanguageParser.DeclContext ctx) {
        String varName = ctx.ID().getText();
        if (ctx.BOOLEANO() != null) {
            if (ctx.ABRECOL() != null) {
                int size = Integer.parseInt(ctx.NUMBER().getText());
                symbols.declareBooleanArray(varName, size);
            } else {
                symbols.declareBoolean(varName);
            }
        } else {
            if (ctx.ABRECOL() != null) {
                int size = Integer.parseInt(ctx.NUMBER().getText());
                symbols.declareIntArray(varName, size);
            } else {
                symbols.declareInt(varName);
            }
        }
    }

    @Override
    public void exitAssign(EasyLanguageParser.AssignContext ctx) {
        if (ctx.ABRECOL() != null) {
            // Atribuição em array: ID[expr] = expr
            String arrayName = ctx.ID().getText();
            IExpr indexExpr = buildExpr(ctx.expr(0));
            IExpr valueExpr = buildExpr(ctx.expr(1));
            Object array = symbols.get(arrayName);
            int index = indexExpr.evalAsInt(symbols);
            
            if (array instanceof int[]) {
                ((int[])array)[index] = valueExpr.evalAsInt(symbols);
            } else if (array instanceof boolean[]) {
                ((boolean[])array)[index] = valueExpr.evalAsBool(symbols);
            } else {
                throw new RuntimeException("Tipo de array não suportado: " + array.getClass());
            }
        } else {
            // Atribuição simples: ID = expr
            String varName = ctx.ID().getText();
            IExpr expr = buildExpr(ctx.expr(0));
            int value = expr.evalAsInt(symbols);
            symbols.assign(varName, value);
        }
    }

    @Override
    public void exitWrite(EasyLanguageParser.WriteContext ctx) {
        IExpr expr = buildExpr(ctx.expr());
        System.out.println(expr.evalAsInt(symbols));
    }

    @Override
    public void exitParaCmd(EasyLanguageParser.ParaCmdContext ctx) {
        String var = ctx.ID().getText();
        IExpr start = buildExpr(ctx.expr(0));
        IExpr end = buildExpr(ctx.expr(1));
        IExpr step = ctx.expr().size() > 2 ? buildExpr(ctx.expr(2)) : null;
        List<ICommand> body = buildCommands(ctx.command());

        CommandPara loop = new CommandPara(var, start, end, step, body);
        loop.execute(symbols); // executa imediatamente
    }

    // -------- Helpers --------

    private IExpr buildExpr(EasyLanguageParser.ExprContext ctx) {
        // Trate cada subclasse gerada de ExprContext explicitamente
        if (ctx instanceof EasyLanguageParser.NumberExprContext) {
            EasyLanguageParser.NumberExprContext nctx = (EasyLanguageParser.NumberExprContext) ctx;
            final int value = Integer.parseInt(nctx.NUMBER().getText());
            return new IExpr() {
                @Override
                public int eval(SymbolTable symbols) {
                    return value;
                }
            };
        }

        if (ctx instanceof EasyLanguageParser.TrueExprContext) {
            return new BoolExpr(true);
        }

        if (ctx instanceof EasyLanguageParser.FalseExprContext) {
            return new BoolExpr(false);
        }

        if (ctx instanceof EasyLanguageParser.VarExprContext) {
            EasyLanguageParser.VarExprContext vctx = (EasyLanguageParser.VarExprContext) ctx;
            return new VarExpr(vctx.ID().getText());
        }

        if (ctx instanceof EasyLanguageParser.ArrayAccessExprContext) {
            EasyLanguageParser.ArrayAccessExprContext actx = (EasyLanguageParser.ArrayAccessExprContext) ctx;
            String arrayName = actx.ID().getText();
            IExpr index = buildExpr(actx.expr());
            return new ArrayAccessExpr(arrayName, index);
        }

        if (ctx instanceof EasyLanguageParser.AndExprContext) {
            EasyLanguageParser.AndExprContext actx = (EasyLanguageParser.AndExprContext) ctx;
            return new AndExpr(buildExpr(actx.expr(0)), buildExpr(actx.expr(1)));
        }

        if (ctx instanceof EasyLanguageParser.OrExprContext) {
            EasyLanguageParser.OrExprContext octx = (EasyLanguageParser.OrExprContext) ctx;
            return new OrExpr(buildExpr(octx.expr(0)), buildExpr(octx.expr(1)));
        }

        if (ctx instanceof EasyLanguageParser.NotExprContext) {
            EasyLanguageParser.NotExprContext nctx = (EasyLanguageParser.NotExprContext) ctx;
            return new NotExpr(buildExpr(nctx.expr()));
        }

        // Operações binárias: trate cada tipo de expressão binária explicitamente
        if (ctx instanceof EasyLanguageParser.AddExprContext) {
            EasyLanguageParser.AddExprContext c = (EasyLanguageParser.AddExprContext) ctx;
            IExpr left = buildExpr(c.expr(0));
            IExpr right = buildExpr(c.expr(1));
            return new AddExpr(left, right);
        }

        if (ctx instanceof EasyLanguageParser.SubExprContext) {
            EasyLanguageParser.SubExprContext c = (EasyLanguageParser.SubExprContext) ctx;
            IExpr left = buildExpr(c.expr(0));
            IExpr right = buildExpr(c.expr(1));
            return new SubExpr(left, right);
        }

        if (ctx instanceof EasyLanguageParser.MulExprContext) {
            EasyLanguageParser.MulExprContext c = (EasyLanguageParser.MulExprContext) ctx;
            IExpr left = buildExpr(c.expr(0));
            IExpr right = buildExpr(c.expr(1));
            return new MulExpr(left, right);
        }

        if (ctx instanceof EasyLanguageParser.DivExprContext) {
            EasyLanguageParser.DivExprContext c = (EasyLanguageParser.DivExprContext) ctx;
            IExpr left = buildExpr(c.expr(0));
            IExpr right = buildExpr(c.expr(1));
            return new DivExpr(left, right);
        }

        if (ctx instanceof EasyLanguageParser.LtExprContext) {
            EasyLanguageParser.LtExprContext c = (EasyLanguageParser.LtExprContext) ctx;
            IExpr left = buildExpr(c.expr(0));
            IExpr right = buildExpr(c.expr(1));
            return new RelationalExpr(left, "<", right);
        }

        if (ctx instanceof EasyLanguageParser.GtExprContext) {
            EasyLanguageParser.GtExprContext c = (EasyLanguageParser.GtExprContext) ctx;
            IExpr left = buildExpr(c.expr(0));
            IExpr right = buildExpr(c.expr(1));
            return new RelationalExpr(left, ">", right);
        }

        if (ctx instanceof EasyLanguageParser.EqExprContext) {
            EasyLanguageParser.EqExprContext c = (EasyLanguageParser.EqExprContext) ctx;
            IExpr left = buildExpr(c.expr(0));
            IExpr right = buildExpr(c.expr(1));
            return new RelationalExpr(left, "==", right);
        }

        if (ctx instanceof EasyLanguageParser.NeqExprContext) {
            EasyLanguageParser.NeqExprContext c = (EasyLanguageParser.NeqExprContext) ctx;
            IExpr left = buildExpr(c.expr(0));
            IExpr right = buildExpr(c.expr(1));
            return new RelationalExpr(left, "!=", right);
        }

        if (ctx instanceof EasyLanguageParser.LtExprContext) {
            EasyLanguageParser.LtExprContext lctx = (EasyLanguageParser.LtExprContext) ctx;
            return new RelationalExpr("<", buildExpr(lctx.expr(0)), buildExpr(lctx.expr(1)));
        }

        if (ctx instanceof EasyLanguageParser.GtExprContext) {
            EasyLanguageParser.GtExprContext gctx = (EasyLanguageParser.GtExprContext) ctx;
            return new RelationalExpr(">", buildExpr(gctx.expr(0)), buildExpr(gctx.expr(1)));
        }

        if (ctx instanceof EasyLanguageParser.EqExprContext) {
            EasyLanguageParser.EqExprContext ectx = (EasyLanguageParser.EqExprContext) ctx;
            return new RelationalExpr("==", buildExpr(ectx.expr(0)), buildExpr(ectx.expr(1)));
        }

        if (ctx instanceof EasyLanguageParser.NeqExprContext) {
            EasyLanguageParser.NeqExprContext nctx = (EasyLanguageParser.NeqExprContext) ctx;
            return new RelationalExpr("!=", buildExpr(nctx.expr(0)), buildExpr(nctx.expr(1)));
        }

        if (ctx instanceof EasyLanguageParser.ParenExprContext) {
            EasyLanguageParser.ParenExprContext pctx = (EasyLanguageParser.ParenExprContext) ctx;
            return buildExpr(pctx.expr());
        }

        throw new RuntimeException("Tipo de expressão não suportado: " + ctx.getText());
    }

    private List<ICommand> buildCommands(List<EasyLanguageParser.CommandContext> commands) {
        List<ICommand> result = new ArrayList<>();
        for (EasyLanguageParser.CommandContext cmdCtx : commands) {
            if (cmdCtx.decl() != null) {
                String varName = cmdCtx.decl().ID().getText();
                result.add(new ICommand() {
                    @Override
                    public void execute(SymbolTable symbols) {
                        symbols.declareInt(varName);
                    }
                });
            } else if (cmdCtx.assign() != null) {
                String varName = cmdCtx.assign().ID().getText();
                IExpr expr = buildExpr(cmdCtx.assign().expr(0));
                result.add(new ICommand() {
                    @Override
                    public void execute(SymbolTable symbols) {
                        symbols.assign(varName, expr.evalAsInt(symbols));
                    }
                });
            } else if (cmdCtx.write() != null) {
                IExpr expr = buildExpr(cmdCtx.write().expr());
                result.add(new ICommand() {
                    @Override
                    public void execute(SymbolTable symbols) {
                        System.out.println(expr.evalAsInt(symbols));
                    }
                });
            } else if (cmdCtx.paraCmd() != null) {
                // suporte a laços aninhados
                result.add(new ICommand() {
                    @Override
                    public void execute(SymbolTable symbols) {
                        String var = cmdCtx.paraCmd().ID().getText();
                        IExpr start = buildExpr(cmdCtx.paraCmd().expr(0));
                        IExpr end = buildExpr(cmdCtx.paraCmd().expr(1));
                        IExpr step = cmdCtx.paraCmd().expr().size() > 2 ? buildExpr(cmdCtx.paraCmd().expr(2)) : null;
                        List<ICommand> body = buildCommands(cmdCtx.paraCmd().command());
                        new CommandPara(var, start, end, step, body).execute(symbols);
                    }
                });
            }
            // depois você pode adicionar enquantoCmd aqui também
        }
        return result;
    }
}