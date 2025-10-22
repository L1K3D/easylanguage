// Generated from com/easylanguage/EasyLanguage.g4 by ANTLR 4.13.1
package com.easylanguage;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link EasyLanguageParser}.
 */
public interface EasyLanguageListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link EasyLanguageParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(EasyLanguageParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link EasyLanguageParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(EasyLanguageParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link EasyLanguageParser#command}.
	 * @param ctx the parse tree
	 */
	void enterCommand(EasyLanguageParser.CommandContext ctx);
	/**
	 * Exit a parse tree produced by {@link EasyLanguageParser#command}.
	 * @param ctx the parse tree
	 */
	void exitCommand(EasyLanguageParser.CommandContext ctx);
	/**
	 * Enter a parse tree produced by {@link EasyLanguageParser#decl}.
	 * @param ctx the parse tree
	 */
	void enterDecl(EasyLanguageParser.DeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link EasyLanguageParser#decl}.
	 * @param ctx the parse tree
	 */
	void exitDecl(EasyLanguageParser.DeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link EasyLanguageParser#assign}.
	 * @param ctx the parse tree
	 */
	void enterAssign(EasyLanguageParser.AssignContext ctx);
	/**
	 * Exit a parse tree produced by {@link EasyLanguageParser#assign}.
	 * @param ctx the parse tree
	 */
	void exitAssign(EasyLanguageParser.AssignContext ctx);
	/**
	 * Enter a parse tree produced by {@link EasyLanguageParser#write}.
	 * @param ctx the parse tree
	 */
	void enterWrite(EasyLanguageParser.WriteContext ctx);
	/**
	 * Exit a parse tree produced by {@link EasyLanguageParser#write}.
	 * @param ctx the parse tree
	 */
	void exitWrite(EasyLanguageParser.WriteContext ctx);
	/**
	 * Enter a parse tree produced by {@link EasyLanguageParser#enquantoCmd}.
	 * @param ctx the parse tree
	 */
	void enterEnquantoCmd(EasyLanguageParser.EnquantoCmdContext ctx);
	/**
	 * Exit a parse tree produced by {@link EasyLanguageParser#enquantoCmd}.
	 * @param ctx the parse tree
	 */
	void exitEnquantoCmd(EasyLanguageParser.EnquantoCmdContext ctx);
	/**
	 * Enter a parse tree produced by {@link EasyLanguageParser#paraCmd}.
	 * @param ctx the parse tree
	 */
	void enterParaCmd(EasyLanguageParser.ParaCmdContext ctx);
	/**
	 * Exit a parse tree produced by {@link EasyLanguageParser#paraCmd}.
	 * @param ctx the parse tree
	 */
	void exitParaCmd(EasyLanguageParser.ParaCmdContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MulExpr}
	 * labeled alternative in {@link EasyLanguageParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterMulExpr(EasyLanguageParser.MulExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MulExpr}
	 * labeled alternative in {@link EasyLanguageParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitMulExpr(EasyLanguageParser.MulExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AndExpr}
	 * labeled alternative in {@link EasyLanguageParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterAndExpr(EasyLanguageParser.AndExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AndExpr}
	 * labeled alternative in {@link EasyLanguageParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitAndExpr(EasyLanguageParser.AndExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code TrueExpr}
	 * labeled alternative in {@link EasyLanguageParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterTrueExpr(EasyLanguageParser.TrueExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code TrueExpr}
	 * labeled alternative in {@link EasyLanguageParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitTrueExpr(EasyLanguageParser.TrueExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ArrayAccessExpr}
	 * labeled alternative in {@link EasyLanguageParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterArrayAccessExpr(EasyLanguageParser.ArrayAccessExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ArrayAccessExpr}
	 * labeled alternative in {@link EasyLanguageParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitArrayAccessExpr(EasyLanguageParser.ArrayAccessExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code SubExpr}
	 * labeled alternative in {@link EasyLanguageParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterSubExpr(EasyLanguageParser.SubExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code SubExpr}
	 * labeled alternative in {@link EasyLanguageParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitSubExpr(EasyLanguageParser.SubExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code LtExpr}
	 * labeled alternative in {@link EasyLanguageParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterLtExpr(EasyLanguageParser.LtExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code LtExpr}
	 * labeled alternative in {@link EasyLanguageParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitLtExpr(EasyLanguageParser.LtExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code GtExpr}
	 * labeled alternative in {@link EasyLanguageParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterGtExpr(EasyLanguageParser.GtExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code GtExpr}
	 * labeled alternative in {@link EasyLanguageParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitGtExpr(EasyLanguageParser.GtExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AddExpr}
	 * labeled alternative in {@link EasyLanguageParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterAddExpr(EasyLanguageParser.AddExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AddExpr}
	 * labeled alternative in {@link EasyLanguageParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitAddExpr(EasyLanguageParser.AddExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code OrExpr}
	 * labeled alternative in {@link EasyLanguageParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterOrExpr(EasyLanguageParser.OrExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code OrExpr}
	 * labeled alternative in {@link EasyLanguageParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitOrExpr(EasyLanguageParser.OrExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code FalseExpr}
	 * labeled alternative in {@link EasyLanguageParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterFalseExpr(EasyLanguageParser.FalseExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code FalseExpr}
	 * labeled alternative in {@link EasyLanguageParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitFalseExpr(EasyLanguageParser.FalseExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code DivExpr}
	 * labeled alternative in {@link EasyLanguageParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterDivExpr(EasyLanguageParser.DivExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code DivExpr}
	 * labeled alternative in {@link EasyLanguageParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitDivExpr(EasyLanguageParser.DivExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NumberExpr}
	 * labeled alternative in {@link EasyLanguageParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterNumberExpr(EasyLanguageParser.NumberExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NumberExpr}
	 * labeled alternative in {@link EasyLanguageParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitNumberExpr(EasyLanguageParser.NumberExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code EqExpr}
	 * labeled alternative in {@link EasyLanguageParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterEqExpr(EasyLanguageParser.EqExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code EqExpr}
	 * labeled alternative in {@link EasyLanguageParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitEqExpr(EasyLanguageParser.EqExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NeqExpr}
	 * labeled alternative in {@link EasyLanguageParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterNeqExpr(EasyLanguageParser.NeqExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NeqExpr}
	 * labeled alternative in {@link EasyLanguageParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitNeqExpr(EasyLanguageParser.NeqExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code VarExpr}
	 * labeled alternative in {@link EasyLanguageParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterVarExpr(EasyLanguageParser.VarExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code VarExpr}
	 * labeled alternative in {@link EasyLanguageParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitVarExpr(EasyLanguageParser.VarExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NotExpr}
	 * labeled alternative in {@link EasyLanguageParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterNotExpr(EasyLanguageParser.NotExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NotExpr}
	 * labeled alternative in {@link EasyLanguageParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitNotExpr(EasyLanguageParser.NotExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ParenExpr}
	 * labeled alternative in {@link EasyLanguageParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterParenExpr(EasyLanguageParser.ParenExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ParenExpr}
	 * labeled alternative in {@link EasyLanguageParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitParenExpr(EasyLanguageParser.ParenExprContext ctx);
}