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
            Object value = expr.eval(symbols);
            symbols.assign(varName, value);
        }
    }

    @Override
    public void exitWrite(EasyLanguageParser.WriteContext ctx) {
        IExpr expr = buildExpr(ctx.expr());
        System.out.println(expr.eval(symbols));
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
        if (ctx.NUMBER() != null) {
            final int value = Integer.parseInt(ctx.NUMBER().getText());
            return new IExpr() {
                @Override
                public Object eval(SymbolTable symbols) {
                    return value;
                }
            };
        }
        if (ctx.VERDADEIRO() != null) {
            return new BoolExpr(true);
        }
        if (ctx.FALSO() != null) {
            return new BoolExpr(false);
        }
        if (ctx.ID() != null) {
            if (ctx.ABRECOL() != null) {
                // Acesso a array: ID[expr]
                String arrayName = ctx.ID().getText();
                IExpr index = buildExpr(ctx.expr(0));
                return new ArrayAccessExpr(arrayName, index);
            }
            return new VarExpr(ctx.ID().getText());
        }
        if (ctx.E() != null) {
            // Operador E: expr E expr
            return new AndExpr(buildExpr(ctx.expr(0)), buildExpr(ctx.expr(1)));
        }
        if (ctx.OU() != null) {
            // Operador OU: expr OU expr
            return new OrExpr(buildExpr(ctx.expr(0)), buildExpr(ctx.expr(1)));
        }
        if (ctx.NAO() != null) {
            // Operador NAO: NAO expr
            return new NotExpr(buildExpr(ctx.expr(0)));
        }
        // Operadores relacionais
        if (ctx.MENOR() != null) {
            return new RelationalExpr("<", buildExpr(ctx.expr(0)), buildExpr(ctx.expr(1)));
        }
        if (ctx.MAIOR() != null) {
            return new RelationalExpr(">", buildExpr(ctx.expr(0)), buildExpr(ctx.expr(1)));
        }
        if (ctx.IGUAL() != null) {
            return new RelationalExpr("==", buildExpr(ctx.expr(0)), buildExpr(ctx.expr(1)));
        }
        if (ctx.DIFERENTE() != null) {
            return new RelationalExpr("!=", buildExpr(ctx.expr(0)), buildExpr(ctx.expr(1)));
        }
        if (ctx.LPAREN() != null) {
            // Parênteses: (expr)
            return buildExpr(ctx.expr(0));
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
                        System.out.println(expr.eval(symbols));
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