grammar EasyLanguage;

// -------- TOKENS --------
INT       : 'int';
BOOLEANO  : 'boolean';
FUNC      : 'func';
PROC      : 'proc';
RETURN    : 'return';
WRITE     : 'escreva';
ENQUANTO  : 'enquanto';
FACA      : 'faca';
PARA      : 'para';
DE        : 'de';
ATE       : 'ate';
PASSO     : 'passo';
FIM       : 'fim';

VERDADEIRO: 'verdadeiro';
FALSO     : 'falso';
E         : 'e';
OU        : 'ou';
NAO       : 'nao';

PLUS      : '+';
MINUS     : '-';
MUL       : '*';
DIV       : '/';

MENOR     : '<';
MAIOR     : '>';
IGUAL     : '==';
DIFERENTE : '!=';

ID        : [a-zA-Z_][a-zA-Z0-9_]*;
NUMBER    : [0-9]+;

SEMI      : ';';
EQ        : '=';
LPAREN    : '(';
RPAREN    : ')';
ABRECOL   : '[';
FECHACOL  : ']';
COMMA     : ',';
COLON     : ':';

WS        : [ \t\r\n]+ -> skip;

// -------- REGRAS --------
program
    : (decl | funcDecl | procDecl | command)* EOF
    ;

command
    : assign
    | write
    | enquantoCmd
    | paraCmd
    | call SEMI
    | returnStmt
    ;

decl
    : tipo ID SEMI
    | tipo ID ABRECOL NUMBER FECHACOL SEMI
    ;

tipo
    : INT
    | BOOLEANO
    ;

funcDecl
    : FUNC ID LPAREN paramList? RPAREN COLON tipo FACA (decl | command)* FIM
    ;

procDecl
    : PROC ID LPAREN paramList? RPAREN FACA (decl | command)* FIM
    ;

paramList
    : param (COMMA param)*
    ;

param
    : tipo ID
    ;

assign
    : ID EQ expr SEMI
    | ID ABRECOL expr FECHACOL EQ expr SEMI
    ;

write
    : WRITE LPAREN expr RPAREN SEMI
    ;

enquantoCmd
    : ENQUANTO LPAREN expr RPAREN FACA command*
    ;

paraCmd
    : PARA ID DE expr ATE expr (PASSO expr)? FACA command*
    ;

call
    : ID LPAREN argList? RPAREN
    ;

argList
    : expr (COMMA expr)*
    ;

returnStmt
    : RETURN expr SEMI
    ;

// -------- EXPRESSÕES --------
expr
    : expr MUL expr                       # MulExpr
    | expr DIV expr                       # DivExpr
    | expr PLUS expr                      # AddExpr
    | expr MINUS expr                     # SubExpr
    | expr MENOR expr                     # LtExpr
    | expr MAIOR expr                     # GtExpr
    | expr IGUAL expr                     # EqExpr
    | expr DIFERENTE expr                 # NeqExpr
    | expr E expr                         # AndExpr
    | expr OU expr                        # OrExpr
    | NAO expr                            # NotExpr
    | NUMBER                              # NumberExpr
    | VERDADEIRO                          # TrueExpr
    | FALSO                               # FalseExpr
    | ID                                  # VarExpr
    | ID ABRECOL expr FECHACOL            # ArrayAccessExpr
    | call                                # CallExpr
    | LPAREN expr RPAREN                  # ParenExpr
    ;