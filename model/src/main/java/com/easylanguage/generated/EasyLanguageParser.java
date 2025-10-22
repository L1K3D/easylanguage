package com.easylanguage.generated;
// Generated from ./src/main/antlr4/com/easylanguage/EasyLanguage.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class EasyLanguageParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		INT=1, BOOLEANO=2, WRITE=3, ENQUANTO=4, FACA=5, PARA=6, DE=7, ATE=8, PASSO=9, 
		VERDADEIRO=10, FALSO=11, E=12, OU=13, NAO=14, PLUS=15, MINUS=16, MUL=17, 
		DIV=18, MENOR=19, MAIOR=20, IGUAL=21, DIFERENTE=22, ID=23, NUMBER=24, 
		SEMI=25, EQ=26, LPAREN=27, RPAREN=28, ABRECOL=29, FECHACOL=30, WS=31;
	public static final int
		RULE_program = 0, RULE_command = 1, RULE_decl = 2, RULE_assign = 3, RULE_write = 4, 
		RULE_enquantoCmd = 5, RULE_paraCmd = 6, RULE_expr = 7;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "command", "decl", "assign", "write", "enquantoCmd", "paraCmd", 
			"expr"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'int'", "'boolean'", "'escreva'", "'enquanto'", "'faca'", "'para'", 
			"'de'", "'ate'", "'passo'", "'verdadeiro'", "'falso'", "'e'", "'ou'", 
			"'nao'", "'+'", "'-'", "'*'", "'/'", "'<'", "'>'", "'=='", "'!='", null, 
			null, "';'", "'='", "'('", "')'", "'['", "']'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "INT", "BOOLEANO", "WRITE", "ENQUANTO", "FACA", "PARA", "DE", "ATE", 
			"PASSO", "VERDADEIRO", "FALSO", "E", "OU", "NAO", "PLUS", "MINUS", "MUL", 
			"DIV", "MENOR", "MAIOR", "IGUAL", "DIFERENTE", "ID", "NUMBER", "SEMI", 
			"EQ", "LPAREN", "RPAREN", "ABRECOL", "FECHACOL", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "EasyLanguage.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public EasyLanguageParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProgramContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(EasyLanguageParser.EOF, 0); }
		public List<CommandContext> command() {
			return getRuleContexts(CommandContext.class);
		}
		public CommandContext command(int i) {
			return getRuleContext(CommandContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EasyLanguageListener ) ((EasyLanguageListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EasyLanguageListener ) ((EasyLanguageListener)listener).exitProgram(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(19);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 8388702L) != 0)) {
				{
				{
				setState(16);
				command();
				}
				}
				setState(21);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(22);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CommandContext extends ParserRuleContext {
		public DeclContext decl() {
			return getRuleContext(DeclContext.class,0);
		}
		public AssignContext assign() {
			return getRuleContext(AssignContext.class,0);
		}
		public WriteContext write() {
			return getRuleContext(WriteContext.class,0);
		}
		public EnquantoCmdContext enquantoCmd() {
			return getRuleContext(EnquantoCmdContext.class,0);
		}
		public ParaCmdContext paraCmd() {
			return getRuleContext(ParaCmdContext.class,0);
		}
		public CommandContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_command; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EasyLanguageListener ) ((EasyLanguageListener)listener).enterCommand(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EasyLanguageListener ) ((EasyLanguageListener)listener).exitCommand(this);
		}
	}

	public final CommandContext command() throws RecognitionException {
		CommandContext _localctx = new CommandContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_command);
		try {
			setState(29);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INT:
			case BOOLEANO:
				enterOuterAlt(_localctx, 1);
				{
				setState(24);
				decl();
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 2);
				{
				setState(25);
				assign();
				}
				break;
			case WRITE:
				enterOuterAlt(_localctx, 3);
				{
				setState(26);
				write();
				}
				break;
			case ENQUANTO:
				enterOuterAlt(_localctx, 4);
				{
				setState(27);
				enquantoCmd();
				}
				break;
			case PARA:
				enterOuterAlt(_localctx, 5);
				{
				setState(28);
				paraCmd();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DeclContext extends ParserRuleContext {
		public TerminalNode INT() { return getToken(EasyLanguageParser.INT, 0); }
		public TerminalNode ID() { return getToken(EasyLanguageParser.ID, 0); }
		public TerminalNode SEMI() { return getToken(EasyLanguageParser.SEMI, 0); }
		public TerminalNode BOOLEANO() { return getToken(EasyLanguageParser.BOOLEANO, 0); }
		public TerminalNode ABRECOL() { return getToken(EasyLanguageParser.ABRECOL, 0); }
		public TerminalNode NUMBER() { return getToken(EasyLanguageParser.NUMBER, 0); }
		public TerminalNode FECHACOL() { return getToken(EasyLanguageParser.FECHACOL, 0); }
		public DeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EasyLanguageListener ) ((EasyLanguageListener)listener).enterDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EasyLanguageListener ) ((EasyLanguageListener)listener).exitDecl(this);
		}
	}

	public final DeclContext decl() throws RecognitionException {
		DeclContext _localctx = new DeclContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_decl);
		try {
			setState(49);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(31);
				match(INT);
				setState(32);
				match(ID);
				setState(33);
				match(SEMI);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(34);
				match(BOOLEANO);
				setState(35);
				match(ID);
				setState(36);
				match(SEMI);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(37);
				match(INT);
				setState(38);
				match(ID);
				setState(39);
				match(ABRECOL);
				setState(40);
				match(NUMBER);
				setState(41);
				match(FECHACOL);
				setState(42);
				match(SEMI);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(43);
				match(BOOLEANO);
				setState(44);
				match(ID);
				setState(45);
				match(ABRECOL);
				setState(46);
				match(NUMBER);
				setState(47);
				match(FECHACOL);
				setState(48);
				match(SEMI);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AssignContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(EasyLanguageParser.ID, 0); }
		public TerminalNode EQ() { return getToken(EasyLanguageParser.EQ, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode SEMI() { return getToken(EasyLanguageParser.SEMI, 0); }
		public TerminalNode ABRECOL() { return getToken(EasyLanguageParser.ABRECOL, 0); }
		public TerminalNode FECHACOL() { return getToken(EasyLanguageParser.FECHACOL, 0); }
		public AssignContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assign; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EasyLanguageListener ) ((EasyLanguageListener)listener).enterAssign(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EasyLanguageListener ) ((EasyLanguageListener)listener).exitAssign(this);
		}
	}

	public final AssignContext assign() throws RecognitionException {
		AssignContext _localctx = new AssignContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_assign);
		try {
			setState(64);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(51);
				match(ID);
				setState(52);
				match(EQ);
				setState(53);
				expr(0);
				setState(54);
				match(SEMI);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(56);
				match(ID);
				setState(57);
				match(ABRECOL);
				setState(58);
				expr(0);
				setState(59);
				match(FECHACOL);
				setState(60);
				match(EQ);
				setState(61);
				expr(0);
				setState(62);
				match(SEMI);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class WriteContext extends ParserRuleContext {
		public TerminalNode WRITE() { return getToken(EasyLanguageParser.WRITE, 0); }
		public TerminalNode LPAREN() { return getToken(EasyLanguageParser.LPAREN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(EasyLanguageParser.RPAREN, 0); }
		public TerminalNode SEMI() { return getToken(EasyLanguageParser.SEMI, 0); }
		public WriteContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_write; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EasyLanguageListener ) ((EasyLanguageListener)listener).enterWrite(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EasyLanguageListener ) ((EasyLanguageListener)listener).exitWrite(this);
		}
	}

	public final WriteContext write() throws RecognitionException {
		WriteContext _localctx = new WriteContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_write);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(66);
			match(WRITE);
			setState(67);
			match(LPAREN);
			setState(68);
			expr(0);
			setState(69);
			match(RPAREN);
			setState(70);
			match(SEMI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class EnquantoCmdContext extends ParserRuleContext {
		public TerminalNode ENQUANTO() { return getToken(EasyLanguageParser.ENQUANTO, 0); }
		public TerminalNode LPAREN() { return getToken(EasyLanguageParser.LPAREN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(EasyLanguageParser.RPAREN, 0); }
		public TerminalNode FACA() { return getToken(EasyLanguageParser.FACA, 0); }
		public List<CommandContext> command() {
			return getRuleContexts(CommandContext.class);
		}
		public CommandContext command(int i) {
			return getRuleContext(CommandContext.class,i);
		}
		public EnquantoCmdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_enquantoCmd; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EasyLanguageListener ) ((EasyLanguageListener)listener).enterEnquantoCmd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EasyLanguageListener ) ((EasyLanguageListener)listener).exitEnquantoCmd(this);
		}
	}

	public final EnquantoCmdContext enquantoCmd() throws RecognitionException {
		EnquantoCmdContext _localctx = new EnquantoCmdContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_enquantoCmd);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(72);
			match(ENQUANTO);
			setState(73);
			match(LPAREN);
			setState(74);
			expr(0);
			setState(75);
			match(RPAREN);
			setState(76);
			match(FACA);
			setState(80);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(77);
					command();
					}
					} 
				}
				setState(82);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ParaCmdContext extends ParserRuleContext {
		public TerminalNode PARA() { return getToken(EasyLanguageParser.PARA, 0); }
		public TerminalNode ID() { return getToken(EasyLanguageParser.ID, 0); }
		public TerminalNode DE() { return getToken(EasyLanguageParser.DE, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode ATE() { return getToken(EasyLanguageParser.ATE, 0); }
		public TerminalNode FACA() { return getToken(EasyLanguageParser.FACA, 0); }
		public TerminalNode PASSO() { return getToken(EasyLanguageParser.PASSO, 0); }
		public List<CommandContext> command() {
			return getRuleContexts(CommandContext.class);
		}
		public CommandContext command(int i) {
			return getRuleContext(CommandContext.class,i);
		}
		public ParaCmdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_paraCmd; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EasyLanguageListener ) ((EasyLanguageListener)listener).enterParaCmd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EasyLanguageListener ) ((EasyLanguageListener)listener).exitParaCmd(this);
		}
	}

	public final ParaCmdContext paraCmd() throws RecognitionException {
		ParaCmdContext _localctx = new ParaCmdContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_paraCmd);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(83);
			match(PARA);
			setState(84);
			match(ID);
			setState(85);
			match(DE);
			setState(86);
			expr(0);
			setState(87);
			match(ATE);
			setState(88);
			expr(0);
			setState(91);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==PASSO) {
				{
				setState(89);
				match(PASSO);
				setState(90);
				expr(0);
				}
			}

			setState(93);
			match(FACA);
			setState(97);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(94);
					command();
					}
					} 
				}
				setState(99);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExprContext extends ParserRuleContext {
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
	 
		public ExprContext() { }
		public void copyFrom(ExprContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class MulExprContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode MUL() { return getToken(EasyLanguageParser.MUL, 0); }
		public MulExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EasyLanguageListener ) ((EasyLanguageListener)listener).enterMulExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EasyLanguageListener ) ((EasyLanguageListener)listener).exitMulExpr(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class AndExprContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode E() { return getToken(EasyLanguageParser.E, 0); }
		public AndExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EasyLanguageListener ) ((EasyLanguageListener)listener).enterAndExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EasyLanguageListener ) ((EasyLanguageListener)listener).exitAndExpr(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class TrueExprContext extends ExprContext {
		public TerminalNode VERDADEIRO() { return getToken(EasyLanguageParser.VERDADEIRO, 0); }
		public TrueExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EasyLanguageListener ) ((EasyLanguageListener)listener).enterTrueExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EasyLanguageListener ) ((EasyLanguageListener)listener).exitTrueExpr(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ArrayAccessExprContext extends ExprContext {
		public TerminalNode ID() { return getToken(EasyLanguageParser.ID, 0); }
		public TerminalNode ABRECOL() { return getToken(EasyLanguageParser.ABRECOL, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode FECHACOL() { return getToken(EasyLanguageParser.FECHACOL, 0); }
		public ArrayAccessExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EasyLanguageListener ) ((EasyLanguageListener)listener).enterArrayAccessExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EasyLanguageListener ) ((EasyLanguageListener)listener).exitArrayAccessExpr(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class SubExprContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode MINUS() { return getToken(EasyLanguageParser.MINUS, 0); }
		public SubExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EasyLanguageListener ) ((EasyLanguageListener)listener).enterSubExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EasyLanguageListener ) ((EasyLanguageListener)listener).exitSubExpr(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class LtExprContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode MENOR() { return getToken(EasyLanguageParser.MENOR, 0); }
		public LtExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EasyLanguageListener ) ((EasyLanguageListener)listener).enterLtExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EasyLanguageListener ) ((EasyLanguageListener)listener).exitLtExpr(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class GtExprContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode MAIOR() { return getToken(EasyLanguageParser.MAIOR, 0); }
		public GtExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EasyLanguageListener ) ((EasyLanguageListener)listener).enterGtExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EasyLanguageListener ) ((EasyLanguageListener)listener).exitGtExpr(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class AddExprContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode PLUS() { return getToken(EasyLanguageParser.PLUS, 0); }
		public AddExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EasyLanguageListener ) ((EasyLanguageListener)listener).enterAddExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EasyLanguageListener ) ((EasyLanguageListener)listener).exitAddExpr(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class OrExprContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode OU() { return getToken(EasyLanguageParser.OU, 0); }
		public OrExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EasyLanguageListener ) ((EasyLanguageListener)listener).enterOrExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EasyLanguageListener ) ((EasyLanguageListener)listener).exitOrExpr(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class FalseExprContext extends ExprContext {
		public TerminalNode FALSO() { return getToken(EasyLanguageParser.FALSO, 0); }
		public FalseExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EasyLanguageListener ) ((EasyLanguageListener)listener).enterFalseExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EasyLanguageListener ) ((EasyLanguageListener)listener).exitFalseExpr(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class DivExprContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode DIV() { return getToken(EasyLanguageParser.DIV, 0); }
		public DivExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EasyLanguageListener ) ((EasyLanguageListener)listener).enterDivExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EasyLanguageListener ) ((EasyLanguageListener)listener).exitDivExpr(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class NumberExprContext extends ExprContext {
		public TerminalNode NUMBER() { return getToken(EasyLanguageParser.NUMBER, 0); }
		public NumberExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EasyLanguageListener ) ((EasyLanguageListener)listener).enterNumberExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EasyLanguageListener ) ((EasyLanguageListener)listener).exitNumberExpr(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class EqExprContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode IGUAL() { return getToken(EasyLanguageParser.IGUAL, 0); }
		public EqExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EasyLanguageListener ) ((EasyLanguageListener)listener).enterEqExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EasyLanguageListener ) ((EasyLanguageListener)listener).exitEqExpr(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class NeqExprContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode DIFERENTE() { return getToken(EasyLanguageParser.DIFERENTE, 0); }
		public NeqExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EasyLanguageListener ) ((EasyLanguageListener)listener).enterNeqExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EasyLanguageListener ) ((EasyLanguageListener)listener).exitNeqExpr(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class VarExprContext extends ExprContext {
		public TerminalNode ID() { return getToken(EasyLanguageParser.ID, 0); }
		public VarExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EasyLanguageListener ) ((EasyLanguageListener)listener).enterVarExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EasyLanguageListener ) ((EasyLanguageListener)listener).exitVarExpr(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class NotExprContext extends ExprContext {
		public TerminalNode NAO() { return getToken(EasyLanguageParser.NAO, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public NotExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EasyLanguageListener ) ((EasyLanguageListener)listener).enterNotExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EasyLanguageListener ) ((EasyLanguageListener)listener).exitNotExpr(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ParenExprContext extends ExprContext {
		public TerminalNode LPAREN() { return getToken(EasyLanguageParser.LPAREN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(EasyLanguageParser.RPAREN, 0); }
		public ParenExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof EasyLanguageListener ) ((EasyLanguageListener)listener).enterParenExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof EasyLanguageListener ) ((EasyLanguageListener)listener).exitParenExpr(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		return expr(0);
	}

	private ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState);
		ExprContext _prevctx = _localctx;
		int _startState = 14;
		enterRecursionRule(_localctx, 14, RULE_expr, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(116);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				{
				_localctx = new NotExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(101);
				match(NAO);
				setState(102);
				expr(7);
				}
				break;
			case 2:
				{
				_localctx = new NumberExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(103);
				match(NUMBER);
				}
				break;
			case 3:
				{
				_localctx = new TrueExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(104);
				match(VERDADEIRO);
				}
				break;
			case 4:
				{
				_localctx = new FalseExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(105);
				match(FALSO);
				}
				break;
			case 5:
				{
				_localctx = new VarExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(106);
				match(ID);
				}
				break;
			case 6:
				{
				_localctx = new ArrayAccessExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(107);
				match(ID);
				setState(108);
				match(ABRECOL);
				setState(109);
				expr(0);
				setState(110);
				match(FECHACOL);
				}
				break;
			case 7:
				{
				_localctx = new ParenExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(112);
				match(LPAREN);
				setState(113);
				expr(0);
				setState(114);
				match(RPAREN);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(150);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(148);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
					case 1:
						{
						_localctx = new MulExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(118);
						if (!(precpred(_ctx, 17))) throw new FailedPredicateException(this, "precpred(_ctx, 17)");
						setState(119);
						match(MUL);
						setState(120);
						expr(18);
						}
						break;
					case 2:
						{
						_localctx = new DivExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(121);
						if (!(precpred(_ctx, 16))) throw new FailedPredicateException(this, "precpred(_ctx, 16)");
						setState(122);
						match(DIV);
						setState(123);
						expr(17);
						}
						break;
					case 3:
						{
						_localctx = new AddExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(124);
						if (!(precpred(_ctx, 15))) throw new FailedPredicateException(this, "precpred(_ctx, 15)");
						setState(125);
						match(PLUS);
						setState(126);
						expr(16);
						}
						break;
					case 4:
						{
						_localctx = new SubExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(127);
						if (!(precpred(_ctx, 14))) throw new FailedPredicateException(this, "precpred(_ctx, 14)");
						setState(128);
						match(MINUS);
						setState(129);
						expr(15);
						}
						break;
					case 5:
						{
						_localctx = new LtExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(130);
						if (!(precpred(_ctx, 13))) throw new FailedPredicateException(this, "precpred(_ctx, 13)");
						setState(131);
						match(MENOR);
						setState(132);
						expr(14);
						}
						break;
					case 6:
						{
						_localctx = new GtExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(133);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(134);
						match(MAIOR);
						setState(135);
						expr(13);
						}
						break;
					case 7:
						{
						_localctx = new EqExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(136);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(137);
						match(IGUAL);
						setState(138);
						expr(12);
						}
						break;
					case 8:
						{
						_localctx = new NeqExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(139);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(140);
						match(DIFERENTE);
						setState(141);
						expr(11);
						}
						break;
					case 9:
						{
						_localctx = new AndExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(142);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(143);
						match(E);
						setState(144);
						expr(10);
						}
						break;
					case 10:
						{
						_localctx = new OrExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(145);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(146);
						match(OU);
						setState(147);
						expr(9);
						}
						break;
					}
					} 
				}
				setState(152);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 7:
			return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 17);
		case 1:
			return precpred(_ctx, 16);
		case 2:
			return precpred(_ctx, 15);
		case 3:
			return precpred(_ctx, 14);
		case 4:
			return precpred(_ctx, 13);
		case 5:
			return precpred(_ctx, 12);
		case 6:
			return precpred(_ctx, 11);
		case 7:
			return precpred(_ctx, 10);
		case 8:
			return precpred(_ctx, 9);
		case 9:
			return precpred(_ctx, 8);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001\u001f\u009a\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001"+
		"\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004"+
		"\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007"+
		"\u0001\u0000\u0005\u0000\u0012\b\u0000\n\u0000\f\u0000\u0015\t\u0000\u0001"+
		"\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0003\u0001\u001e\b\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0003\u00022\b\u0002\u0001\u0003\u0001"+
		"\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001"+
		"\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0003"+
		"\u0003A\b\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0005\u0005O\b\u0005\n\u0005\f\u0005R\t\u0005\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0003\u0006\\\b\u0006\u0001\u0006\u0001\u0006\u0005"+
		"\u0006`\b\u0006\n\u0006\f\u0006c\t\u0006\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0003\u0007u\b\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0005\u0007\u0095\b\u0007\n"+
		"\u0007\f\u0007\u0098\t\u0007\u0001\u0007\u0000\u0001\u000e\b\u0000\u0002"+
		"\u0004\u0006\b\n\f\u000e\u0000\u0000\u00ad\u0000\u0013\u0001\u0000\u0000"+
		"\u0000\u0002\u001d\u0001\u0000\u0000\u0000\u00041\u0001\u0000\u0000\u0000"+
		"\u0006@\u0001\u0000\u0000\u0000\bB\u0001\u0000\u0000\u0000\nH\u0001\u0000"+
		"\u0000\u0000\fS\u0001\u0000\u0000\u0000\u000et\u0001\u0000\u0000\u0000"+
		"\u0010\u0012\u0003\u0002\u0001\u0000\u0011\u0010\u0001\u0000\u0000\u0000"+
		"\u0012\u0015\u0001\u0000\u0000\u0000\u0013\u0011\u0001\u0000\u0000\u0000"+
		"\u0013\u0014\u0001\u0000\u0000\u0000\u0014\u0016\u0001\u0000\u0000\u0000"+
		"\u0015\u0013\u0001\u0000\u0000\u0000\u0016\u0017\u0005\u0000\u0000\u0001"+
		"\u0017\u0001\u0001\u0000\u0000\u0000\u0018\u001e\u0003\u0004\u0002\u0000"+
		"\u0019\u001e\u0003\u0006\u0003\u0000\u001a\u001e\u0003\b\u0004\u0000\u001b"+
		"\u001e\u0003\n\u0005\u0000\u001c\u001e\u0003\f\u0006\u0000\u001d\u0018"+
		"\u0001\u0000\u0000\u0000\u001d\u0019\u0001\u0000\u0000\u0000\u001d\u001a"+
		"\u0001\u0000\u0000\u0000\u001d\u001b\u0001\u0000\u0000\u0000\u001d\u001c"+
		"\u0001\u0000\u0000\u0000\u001e\u0003\u0001\u0000\u0000\u0000\u001f \u0005"+
		"\u0001\u0000\u0000 !\u0005\u0017\u0000\u0000!2\u0005\u0019\u0000\u0000"+
		"\"#\u0005\u0002\u0000\u0000#$\u0005\u0017\u0000\u0000$2\u0005\u0019\u0000"+
		"\u0000%&\u0005\u0001\u0000\u0000&\'\u0005\u0017\u0000\u0000\'(\u0005\u001d"+
		"\u0000\u0000()\u0005\u0018\u0000\u0000)*\u0005\u001e\u0000\u0000*2\u0005"+
		"\u0019\u0000\u0000+,\u0005\u0002\u0000\u0000,-\u0005\u0017\u0000\u0000"+
		"-.\u0005\u001d\u0000\u0000./\u0005\u0018\u0000\u0000/0\u0005\u001e\u0000"+
		"\u000002\u0005\u0019\u0000\u00001\u001f\u0001\u0000\u0000\u00001\"\u0001"+
		"\u0000\u0000\u00001%\u0001\u0000\u0000\u00001+\u0001\u0000\u0000\u0000"+
		"2\u0005\u0001\u0000\u0000\u000034\u0005\u0017\u0000\u000045\u0005\u001a"+
		"\u0000\u000056\u0003\u000e\u0007\u000067\u0005\u0019\u0000\u00007A\u0001"+
		"\u0000\u0000\u000089\u0005\u0017\u0000\u00009:\u0005\u001d\u0000\u0000"+
		":;\u0003\u000e\u0007\u0000;<\u0005\u001e\u0000\u0000<=\u0005\u001a\u0000"+
		"\u0000=>\u0003\u000e\u0007\u0000>?\u0005\u0019\u0000\u0000?A\u0001\u0000"+
		"\u0000\u0000@3\u0001\u0000\u0000\u0000@8\u0001\u0000\u0000\u0000A\u0007"+
		"\u0001\u0000\u0000\u0000BC\u0005\u0003\u0000\u0000CD\u0005\u001b\u0000"+
		"\u0000DE\u0003\u000e\u0007\u0000EF\u0005\u001c\u0000\u0000FG\u0005\u0019"+
		"\u0000\u0000G\t\u0001\u0000\u0000\u0000HI\u0005\u0004\u0000\u0000IJ\u0005"+
		"\u001b\u0000\u0000JK\u0003\u000e\u0007\u0000KL\u0005\u001c\u0000\u0000"+
		"LP\u0005\u0005\u0000\u0000MO\u0003\u0002\u0001\u0000NM\u0001\u0000\u0000"+
		"\u0000OR\u0001\u0000\u0000\u0000PN\u0001\u0000\u0000\u0000PQ\u0001\u0000"+
		"\u0000\u0000Q\u000b\u0001\u0000\u0000\u0000RP\u0001\u0000\u0000\u0000"+
		"ST\u0005\u0006\u0000\u0000TU\u0005\u0017\u0000\u0000UV\u0005\u0007\u0000"+
		"\u0000VW\u0003\u000e\u0007\u0000WX\u0005\b\u0000\u0000X[\u0003\u000e\u0007"+
		"\u0000YZ\u0005\t\u0000\u0000Z\\\u0003\u000e\u0007\u0000[Y\u0001\u0000"+
		"\u0000\u0000[\\\u0001\u0000\u0000\u0000\\]\u0001\u0000\u0000\u0000]a\u0005"+
		"\u0005\u0000\u0000^`\u0003\u0002\u0001\u0000_^\u0001\u0000\u0000\u0000"+
		"`c\u0001\u0000\u0000\u0000a_\u0001\u0000\u0000\u0000ab\u0001\u0000\u0000"+
		"\u0000b\r\u0001\u0000\u0000\u0000ca\u0001\u0000\u0000\u0000de\u0006\u0007"+
		"\uffff\uffff\u0000ef\u0005\u000e\u0000\u0000fu\u0003\u000e\u0007\u0007"+
		"gu\u0005\u0018\u0000\u0000hu\u0005\n\u0000\u0000iu\u0005\u000b\u0000\u0000"+
		"ju\u0005\u0017\u0000\u0000kl\u0005\u0017\u0000\u0000lm\u0005\u001d\u0000"+
		"\u0000mn\u0003\u000e\u0007\u0000no\u0005\u001e\u0000\u0000ou\u0001\u0000"+
		"\u0000\u0000pq\u0005\u001b\u0000\u0000qr\u0003\u000e\u0007\u0000rs\u0005"+
		"\u001c\u0000\u0000su\u0001\u0000\u0000\u0000td\u0001\u0000\u0000\u0000"+
		"tg\u0001\u0000\u0000\u0000th\u0001\u0000\u0000\u0000ti\u0001\u0000\u0000"+
		"\u0000tj\u0001\u0000\u0000\u0000tk\u0001\u0000\u0000\u0000tp\u0001\u0000"+
		"\u0000\u0000u\u0096\u0001\u0000\u0000\u0000vw\n\u0011\u0000\u0000wx\u0005"+
		"\u0011\u0000\u0000x\u0095\u0003\u000e\u0007\u0012yz\n\u0010\u0000\u0000"+
		"z{\u0005\u0012\u0000\u0000{\u0095\u0003\u000e\u0007\u0011|}\n\u000f\u0000"+
		"\u0000}~\u0005\u000f\u0000\u0000~\u0095\u0003\u000e\u0007\u0010\u007f"+
		"\u0080\n\u000e\u0000\u0000\u0080\u0081\u0005\u0010\u0000\u0000\u0081\u0095"+
		"\u0003\u000e\u0007\u000f\u0082\u0083\n\r\u0000\u0000\u0083\u0084\u0005"+
		"\u0013\u0000\u0000\u0084\u0095\u0003\u000e\u0007\u000e\u0085\u0086\n\f"+
		"\u0000\u0000\u0086\u0087\u0005\u0014\u0000\u0000\u0087\u0095\u0003\u000e"+
		"\u0007\r\u0088\u0089\n\u000b\u0000\u0000\u0089\u008a\u0005\u0015\u0000"+
		"\u0000\u008a\u0095\u0003\u000e\u0007\f\u008b\u008c\n\n\u0000\u0000\u008c"+
		"\u008d\u0005\u0016\u0000\u0000\u008d\u0095\u0003\u000e\u0007\u000b\u008e"+
		"\u008f\n\t\u0000\u0000\u008f\u0090\u0005\f\u0000\u0000\u0090\u0095\u0003"+
		"\u000e\u0007\n\u0091\u0092\n\b\u0000\u0000\u0092\u0093\u0005\r\u0000\u0000"+
		"\u0093\u0095\u0003\u000e\u0007\t\u0094v\u0001\u0000\u0000\u0000\u0094"+
		"y\u0001\u0000\u0000\u0000\u0094|\u0001\u0000\u0000\u0000\u0094\u007f\u0001"+
		"\u0000\u0000\u0000\u0094\u0082\u0001\u0000\u0000\u0000\u0094\u0085\u0001"+
		"\u0000\u0000\u0000\u0094\u0088\u0001\u0000\u0000\u0000\u0094\u008b\u0001"+
		"\u0000\u0000\u0000\u0094\u008e\u0001\u0000\u0000\u0000\u0094\u0091\u0001"+
		"\u0000\u0000\u0000\u0095\u0098\u0001\u0000\u0000\u0000\u0096\u0094\u0001"+
		"\u0000\u0000\u0000\u0096\u0097\u0001\u0000\u0000\u0000\u0097\u000f\u0001"+
		"\u0000\u0000\u0000\u0098\u0096\u0001\u0000\u0000\u0000\n\u0013\u001d1"+
		"@P[at\u0094\u0096";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}