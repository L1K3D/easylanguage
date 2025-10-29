package com.easylanguage.generated;
// Generated from ./src/main/antlr4/com/easylanguage/EasyLanguage.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link EasyLanguageParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface EasyLanguageVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link EasyLanguageParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(EasyLanguageParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link EasyLanguageParser#command}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCommand(EasyLanguageParser.CommandContext ctx);
	/**
	 * Visit a parse tree produced by {@link EasyLanguageParser#decl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDecl(EasyLanguageParser.DeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link EasyLanguageParser#tipo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTipo(EasyLanguageParser.TipoContext ctx);
	/**
	 * Visit a parse tree produced by {@link EasyLanguageParser#funcDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncDecl(EasyLanguageParser.FuncDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link EasyLanguageParser#procDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProcDecl(EasyLanguageParser.ProcDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link EasyLanguageParser#paramList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParamList(EasyLanguageParser.ParamListContext ctx);
	/**
	 * Visit a parse tree produced by {@link EasyLanguageParser#param}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParam(EasyLanguageParser.ParamContext ctx);
	/**
	 * Visit a parse tree produced by {@link EasyLanguageParser#assign}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssign(EasyLanguageParser.AssignContext ctx);
	/**
	 * Visit a parse tree produced by {@link EasyLanguageParser#write}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWrite(EasyLanguageParser.WriteContext ctx);
	/**
	 * Visit a parse tree produced by {@link EasyLanguageParser#enquantoCmd}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEnquantoCmd(EasyLanguageParser.EnquantoCmdContext ctx);
	/**
	 * Visit a parse tree produced by {@link EasyLanguageParser#paraCmd}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParaCmd(EasyLanguageParser.ParaCmdContext ctx);
	/**
	 * Visit a parse tree produced by {@link EasyLanguageParser#call}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCall(EasyLanguageParser.CallContext ctx);
	/**
	 * Visit a parse tree produced by {@link EasyLanguageParser#argList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgList(EasyLanguageParser.ArgListContext ctx);
	/**
	 * Visit a parse tree produced by {@link EasyLanguageParser#returnStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnStmt(EasyLanguageParser.ReturnStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MulExpr}
	 * labeled alternative in {@link EasyLanguageParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMulExpr(EasyLanguageParser.MulExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AndExpr}
	 * labeled alternative in {@link EasyLanguageParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAndExpr(EasyLanguageParser.AndExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code TrueExpr}
	 * labeled alternative in {@link EasyLanguageParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTrueExpr(EasyLanguageParser.TrueExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ArrayAccessExpr}
	 * labeled alternative in {@link EasyLanguageParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayAccessExpr(EasyLanguageParser.ArrayAccessExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code SubExpr}
	 * labeled alternative in {@link EasyLanguageParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubExpr(EasyLanguageParser.SubExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code LtExpr}
	 * labeled alternative in {@link EasyLanguageParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLtExpr(EasyLanguageParser.LtExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code GtExpr}
	 * labeled alternative in {@link EasyLanguageParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGtExpr(EasyLanguageParser.GtExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AddExpr}
	 * labeled alternative in {@link EasyLanguageParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddExpr(EasyLanguageParser.AddExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code OrExpr}
	 * labeled alternative in {@link EasyLanguageParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrExpr(EasyLanguageParser.OrExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code FalseExpr}
	 * labeled alternative in {@link EasyLanguageParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFalseExpr(EasyLanguageParser.FalseExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code DivExpr}
	 * labeled alternative in {@link EasyLanguageParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDivExpr(EasyLanguageParser.DivExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NumberExpr}
	 * labeled alternative in {@link EasyLanguageParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumberExpr(EasyLanguageParser.NumberExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code EqExpr}
	 * labeled alternative in {@link EasyLanguageParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEqExpr(EasyLanguageParser.EqExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NeqExpr}
	 * labeled alternative in {@link EasyLanguageParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNeqExpr(EasyLanguageParser.NeqExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code VarExpr}
	 * labeled alternative in {@link EasyLanguageParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarExpr(EasyLanguageParser.VarExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CallExpr}
	 * labeled alternative in {@link EasyLanguageParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallExpr(EasyLanguageParser.CallExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NotExpr}
	 * labeled alternative in {@link EasyLanguageParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNotExpr(EasyLanguageParser.NotExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ParenExpr}
	 * labeled alternative in {@link EasyLanguageParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenExpr(EasyLanguageParser.ParenExprContext ctx);
}