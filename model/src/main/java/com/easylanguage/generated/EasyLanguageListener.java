package com.easylanguage.generated;
// Generated from ./src/main/antlr4/com/easylanguage/EasyLanguage.g4 by ANTLR 4.13.2
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
	 * Enter a parse tree produced by {@link EasyLanguageParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(EasyLanguageParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link EasyLanguageParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(EasyLanguageParser.ExprContext ctx);
}