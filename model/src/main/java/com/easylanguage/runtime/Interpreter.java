package com.easylanguage.runtime;

import java.util.List;
import java.util.ArrayList;

import com.easylanguage.generated.*;

public class Interpreter extends EasyLanguageBaseListener {
    private SymbolTable symbols = new SymbolTable();
    private FuncDecl currentFunction = null; // Função sendo processada atualmente
    private ProcDecl currentProcedure = null; // Procedimento sendo processado atualmente
    private boolean inFuncOrProc = false; // indica se estamos dentro da declaração de func/proc

    

    @Override
    public void enterFuncDecl(EasyLanguageParser.FuncDeclContext ctx) {
        inFuncOrProc = true;
    }

    @Override
    public void enterProcDecl(EasyLanguageParser.ProcDeclContext ctx) {
        inFuncOrProc = true;
    }

    @Override
    public void exitFuncDecl(EasyLanguageParser.FuncDeclContext ctx) {
        String name = ctx.ID().getText();
        String returnType = ctx.tipo().getText(); // "int" ou "boolean"
        List<FuncDecl.Param> params = new ArrayList<>();

        if (ctx.paramList() != null) {
            for (EasyLanguageParser.ParamContext param : ctx.paramList().param()) {
                String paramType = param.tipo().getText();
                String paramName = param.ID().getText();
                params.add(new FuncDecl.Param(paramType, paramName));
            }
        }

        // Constrói o corpo preservando a ordem de declarações e comandos
        List<ICommand> body = new ArrayList<>();
        if (ctx.children != null) {
            for (org.antlr.v4.runtime.tree.ParseTree child : ctx.children) {
                if (child instanceof EasyLanguageParser.DeclContext) {
                    EasyLanguageParser.DeclContext dctx = (EasyLanguageParser.DeclContext) child;
                    // Cria um ICommand que declara a variável no escopo atual quando executado
                    body.add(new ICommand() {
                        @Override
                        public void execute(SymbolTable symbols) {
                            String varName = dctx.ID().getText();
                            String type = dctx.tipo().getText();
                            if ("boolean".equals(type)) {
                                if (dctx.ABRECOL() != null) {
                                    int size = Integer.parseInt(dctx.NUMBER().getText());
                                    symbols.declareBoolArray(varName, size);
                                } else {
                                    symbols.declareBool(varName);
                                }
                            } else {
                                if (dctx.ABRECOL() != null) {
                                    int size = Integer.parseInt(dctx.NUMBER().getText());
                                    symbols.declareIntArray(varName, size);
                                } else {
                                    symbols.declareInt(varName);
                                }
                            }
                        }
                    });
                } else if (child instanceof EasyLanguageParser.CommandContext) {
                    EasyLanguageParser.CommandContext cctx = (EasyLanguageParser.CommandContext) child;
                    // Reutiliza o builder existente para criar os ICommand
                    body.addAll(buildCommands(java.util.Collections.singletonList(cctx)));
                }
            }
        }

        currentFunction = new FuncDecl(name, returnType, params, body);
        symbols.registerFunction(currentFunction);
        currentFunction = null;
        inFuncOrProc = false;
    }

    @Override
    public void exitProcDecl(EasyLanguageParser.ProcDeclContext ctx) {
        String name = ctx.ID().getText();
        List<ProcDecl.Param> params = new ArrayList<>();

        if (ctx.paramList() != null) {
            for (EasyLanguageParser.ParamContext param : ctx.paramList().param()) {
                String paramType = param.tipo().getText();
                String paramName = param.ID().getText();
                params.add(new ProcDecl.Param(paramType, paramName));
            }
        }

        // Constrói o corpo preservando declarações e comandos
        List<ICommand> body = new ArrayList<>();
        if (ctx.children != null) {
            for (org.antlr.v4.runtime.tree.ParseTree child : ctx.children) {
                if (child instanceof EasyLanguageParser.DeclContext) {
                    EasyLanguageParser.DeclContext dctx = (EasyLanguageParser.DeclContext) child;
                    body.add(new ICommand() {
                        @Override
                        public void execute(SymbolTable symbols) {
                            String varName = dctx.ID().getText();
                            String type = dctx.tipo().getText();
                            if ("boolean".equals(type)) {
                                if (dctx.ABRECOL() != null) {
                                    int size = Integer.parseInt(dctx.NUMBER().getText());
                                    symbols.declareBoolArray(varName, size);
                                } else {
                                    symbols.declareBool(varName);
                                }
                            } else {
                                if (dctx.ABRECOL() != null) {
                                    int size = Integer.parseInt(dctx.NUMBER().getText());
                                    symbols.declareIntArray(varName, size);
                                } else {
                                    symbols.declareInt(varName);
                                }
                            }
                        }
                    });
                } else if (child instanceof EasyLanguageParser.CommandContext) {
                    EasyLanguageParser.CommandContext cctx = (EasyLanguageParser.CommandContext) child;
                    body.addAll(buildCommands(java.util.Collections.singletonList(cctx)));
                }
            }
        }

        currentProcedure = new ProcDecl(name, params, body);
        symbols.registerProcedure(currentProcedure);
        currentProcedure = null;
        inFuncOrProc = false;
    }

    @Override
    public void exitDecl(EasyLanguageParser.DeclContext ctx) {
        // Se estivermos dentro de uma func/proc, não executamos a declaração agora;
        // as declarações internas são transformadas em comandos e inseridas no corpo.
        if (inFuncOrProc) return;

        String varName = ctx.ID().getText();
        String type = ctx.tipo().getText();
        if ("boolean".equals(type)) {
            if (ctx.ABRECOL() != null) {
                int size = Integer.parseInt(ctx.NUMBER().getText());
                symbols.declareBoolArray(varName, size);
            } else {
                symbols.declareBool(varName);
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
        if (inFuncOrProc) return; // não executa atribuições durante parse de corpos de func/proc
        if (ctx.ABRECOL() != null) {
            // Atribuição em array: ID[expr] = expr
            String arrayName = ctx.ID().getText();
            // checagem estática do tipo do elemento
            String arrType = symbols.getType(arrayName);
            if (arrType == null) throw new RuntimeException("Array não declarado: " + arrayName);
            String elemType = arrType.equals("int[]") ? "int" : arrType.equals("boolean[]") ? "boolean" : null;
            if (elemType == null) throw new RuntimeException("Tipo de array não suportado: " + arrType);

            // Inferir tipo do valor
            String valueType = inferType(ctx.expr(1));
            if (!elemType.equals(valueType)) throw new RuntimeException("Tipo incompatível na atribuição ao elemento de array '" + arrayName + "': esperado " + elemType + " mas encontrado " + valueType);

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
            String varType = symbols.getType(varName);
            if (varType == null) throw new RuntimeException("Variável não declarada: " + varName);
            String exprType = inferType(ctx.expr(0));
            if (!varType.equals(exprType)) {
                throw new RuntimeException("Tipo incompatível na atribuição para '" + varName + "': esperado " + varType + " mas encontrado " + exprType);
            }
            IExpr expr = buildExpr(ctx.expr(0));
            int value = expr.evalAsInt(symbols);
            symbols.assign(varName, value);
        }
    }

    @Override
    public void exitWrite(EasyLanguageParser.WriteContext ctx) {
        if (inFuncOrProc) return; // não executa escreva() durante parse de corpos
        IExpr expr = buildExpr(ctx.expr());
        System.out.println(expr.evalAsInt(symbols));
    }

    @Override
    public void exitParaCmd(EasyLanguageParser.ParaCmdContext ctx) {
        if (inFuncOrProc) return; // não executa loops durante parse de corpos
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

        if (ctx instanceof EasyLanguageParser.CallExprContext) {
            EasyLanguageParser.CallExprContext cctx = (EasyLanguageParser.CallExprContext) ctx;
            String name = cctx.call().ID().getText();
            List<IExpr> args = new ArrayList<>();
            if (cctx.call().argList() != null) {
                for (EasyLanguageParser.ExprContext e : cctx.call().argList().expr()) {
                    args.add(buildExpr(e));
                }
            }
            return new CallExpr(name, args);
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



        if (ctx instanceof EasyLanguageParser.ParenExprContext) {
            EasyLanguageParser.ParenExprContext pctx = (EasyLanguageParser.ParenExprContext) ctx;
            return buildExpr(pctx.expr());
        }

        throw new RuntimeException("Tipo de expressão não suportado: " + ctx.getText());
    }

    // Faz inferência de tipo estática para uma expressão do parse tree
    private String inferType(EasyLanguageParser.ExprContext ctx) {
        if (ctx instanceof EasyLanguageParser.NumberExprContext) {
            return "int";
        }
        if (ctx instanceof EasyLanguageParser.TrueExprContext || ctx instanceof EasyLanguageParser.FalseExprContext) {
            return "boolean";
        }
        if (ctx instanceof EasyLanguageParser.VarExprContext) {
            EasyLanguageParser.VarExprContext vctx = (EasyLanguageParser.VarExprContext) ctx;
            String t = symbols.getType(vctx.ID().getText());
            if (t == null) throw new RuntimeException("Variável não declarada (inferência): " + vctx.ID().getText());
            return t;
        }
        if (ctx instanceof EasyLanguageParser.ArrayAccessExprContext) {
            EasyLanguageParser.ArrayAccessExprContext a = (EasyLanguageParser.ArrayAccessExprContext) ctx;
            String t = symbols.getType(a.ID().getText());
            if (t == null) throw new RuntimeException("Variável não declarada (inferência): " + a.ID().getText());
            if (t.equals("int[]")) return "int";
            if (t.equals("boolean[]")) return "boolean";
            throw new RuntimeException("Tipo de array esperado para " + a.ID().getText() + " mas encontrado: " + t);
        }
        if (ctx instanceof EasyLanguageParser.CallExprContext) {
            EasyLanguageParser.CallExprContext cctx = (EasyLanguageParser.CallExprContext) ctx;
            String fname = cctx.call().ID().getText();
            FuncDecl f = symbols.getFunction(fname);
            if (f == null) throw new RuntimeException("Função não encontrada (inferência): " + fname);
            return f.getReturnType();
        }
        if (ctx instanceof EasyLanguageParser.AddExprContext || ctx instanceof EasyLanguageParser.SubExprContext
                || ctx instanceof EasyLanguageParser.MulExprContext || ctx instanceof EasyLanguageParser.DivExprContext) {
            // Safer: inspect generated contexts explicitly
            if (ctx instanceof EasyLanguageParser.AddExprContext) {
                EasyLanguageParser.AddExprContext c = (EasyLanguageParser.AddExprContext) ctx;
                String lt = inferType(c.expr(0));
                String rt = inferType(c.expr(1));
                if (!"int".equals(lt) || !"int".equals(rt)) throw new RuntimeException("Operação aritmética requer 'int' operands: " + ctx.getText());
                return "int";
            }
            if (ctx instanceof EasyLanguageParser.SubExprContext) {
                EasyLanguageParser.SubExprContext c = (EasyLanguageParser.SubExprContext) ctx;
                String lt = inferType(c.expr(0));
                String rt = inferType(c.expr(1));
                if (!"int".equals(lt) || !"int".equals(rt)) throw new RuntimeException("Operação aritmética requer 'int' operands: " + ctx.getText());
                return "int";
            }
            if (ctx instanceof EasyLanguageParser.MulExprContext) {
                EasyLanguageParser.MulExprContext c = (EasyLanguageParser.MulExprContext) ctx;
                String lt = inferType(c.expr(0));
                String rt = inferType(c.expr(1));
                if (!"int".equals(lt) || !"int".equals(rt)) throw new RuntimeException("Operação aritmética requer 'int' operands: " + ctx.getText());
                return "int";
            }
            if (ctx instanceof EasyLanguageParser.DivExprContext) {
                EasyLanguageParser.DivExprContext c = (EasyLanguageParser.DivExprContext) ctx;
                String lt = inferType(c.expr(0));
                String rt = inferType(c.expr(1));
                if (!"int".equals(lt) || !"int".equals(rt)) throw new RuntimeException("Operação aritmética requer 'int' operands: " + ctx.getText());
                return "int";
            }
        }
        if (ctx instanceof EasyLanguageParser.LtExprContext || ctx instanceof EasyLanguageParser.GtExprContext
                || ctx instanceof EasyLanguageParser.EqExprContext || ctx instanceof EasyLanguageParser.NeqExprContext) {
            if (ctx instanceof EasyLanguageParser.LtExprContext) {
                EasyLanguageParser.LtExprContext c = (EasyLanguageParser.LtExprContext) ctx;
                String lt = inferType(c.expr(0));
                String rt = inferType(c.expr(1));
                if (!"int".equals(lt) || !"int".equals(rt)) throw new RuntimeException("Operador relacional requer operandos int: " + ctx.getText());
                return "boolean";
            }
            if (ctx instanceof EasyLanguageParser.GtExprContext) {
                EasyLanguageParser.GtExprContext c = (EasyLanguageParser.GtExprContext) ctx;
                String lt = inferType(c.expr(0));
                String rt = inferType(c.expr(1));
                if (!"int".equals(lt) || !"int".equals(rt)) throw new RuntimeException("Operador relacional requer operandos int: " + ctx.getText());
                return "boolean";
            }
            if (ctx instanceof EasyLanguageParser.EqExprContext) {
                EasyLanguageParser.EqExprContext c = (EasyLanguageParser.EqExprContext) ctx;
                String lt = inferType(c.expr(0));
                String rt = inferType(c.expr(1));
                if (!lt.equals(rt)) throw new RuntimeException("Operador '==' requer operandos do mesmo tipo: " + ctx.getText());
                return "boolean";
            }
            if (ctx instanceof EasyLanguageParser.NeqExprContext) {
                EasyLanguageParser.NeqExprContext c = (EasyLanguageParser.NeqExprContext) ctx;
                String lt = inferType(c.expr(0));
                String rt = inferType(c.expr(1));
                if (!lt.equals(rt)) throw new RuntimeException("Operador '!=' requer operandos do mesmo tipo: " + ctx.getText());
                return "boolean";
            }
        }
        if (ctx instanceof EasyLanguageParser.AndExprContext || ctx instanceof EasyLanguageParser.OrExprContext) {
            EasyLanguageParser.AndExprContext c = (EasyLanguageParser.AndExprContext) ctx;
            String lt = inferType(c.expr(0));
            String rt = inferType(c.expr(1));
            if (!"boolean".equals(lt) || !"boolean".equals(rt)) throw new RuntimeException("Operador lógico requer operandos boolean: " + ctx.getText());
            return "boolean";
        }
        if (ctx instanceof EasyLanguageParser.NotExprContext) {
            EasyLanguageParser.NotExprContext c = (EasyLanguageParser.NotExprContext) ctx;
            String t = inferType(c.expr());
            if (!"boolean".equals(t)) throw new RuntimeException("Operador 'nao' requer expressão boolean: " + ctx.getText());
            return "boolean";
        }
        if (ctx instanceof EasyLanguageParser.ParenExprContext) {
            EasyLanguageParser.ParenExprContext p = (EasyLanguageParser.ParenExprContext) ctx;
            return inferType(p.expr());
        }
        throw new RuntimeException("Não foi possível inferir tipo para expressão: " + ctx.getText());
    }

    private List<ICommand> buildCommands(List<EasyLanguageParser.CommandContext> commands) {
        List<ICommand> result = new ArrayList<>();
        for (EasyLanguageParser.CommandContext cmdCtx : commands) {
            if (cmdCtx.assign() != null) {
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
            } else if (cmdCtx.returnStmt() != null) {
                IExpr returnExpr = buildExpr(cmdCtx.returnStmt().expr());
                result.add(new ReturnCommand(returnExpr));
            } else if (cmdCtx.call() != null) {
                String name = cmdCtx.call().ID().getText();
                List<IExpr> args = new ArrayList<>();
                List<EasyLanguageParser.ExprContext> argExprs = new ArrayList<>();
                if (cmdCtx.call().argList() != null) {
                    for (EasyLanguageParser.ExprContext expr : cmdCtx.call().argList().expr()) {
                        args.add(buildExpr(expr));
                        argExprs.add(expr);
                    }
                }
                // Checagem estática de tipos contra a definição da função/procedimento
                FuncDecl f = symbols.getFunction(name);
                if (f != null) {
                    if (f.getParams().size() != argExprs.size()) throw new RuntimeException("Número de argumentos inválido para função: " + name);
                    for (int i = 0; i < f.getParams().size(); i++) {
                        String expected = f.getParams().get(i).type;
                        String actual = inferType(argExprs.get(i));
                        if (!expected.equals(actual)) throw new RuntimeException("Tipo de argumento inválido na chamada a '" + name + "': esperado " + expected + " mas encontrado " + actual);
                    }
                } else {
                    ProcDecl p = symbols.getProcedure(name);
                    if (p != null) {
                        if (p.getParams().size() != argExprs.size()) throw new RuntimeException("Número de argumentos inválido para procedimento: " + name);
                        for (int i = 0; i < p.getParams().size(); i++) {
                            String expected = p.getParams().get(i).type;
                            String actual = inferType(argExprs.get(i));
                            if (!expected.equals(actual)) throw new RuntimeException("Tipo de argumento inválido na chamada a '" + name + "': esperado " + expected + " mas encontrado " + actual);
                        }
                    }
                }

                result.add(new CallCommand(name, args));
            }
        }
        return result;
    }

    @Override
    public void exitCommand(EasyLanguageParser.CommandContext ctx) {
        // Executa chamadas top-level (não dentro de corpos de func/proc)
        if (inFuncOrProc) return;
        if (ctx.call() != null) {
            String name = ctx.call().ID().getText();
            java.util.List<IExpr> args = new java.util.ArrayList<>();
            if (ctx.call().argList() != null) {
                for (EasyLanguageParser.ExprContext expr : ctx.call().argList().expr()) {
                    args.add(buildExpr(expr));
                }
            }
            new CallCommand(name, args).execute(symbols);
        }
    }
}