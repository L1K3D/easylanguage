grammar EasyLanguage;

// -------- TOKENS --------
INT       : 'int';
BOOLEANO  : 'boolean';
WRITE     : 'escreva';
ENQUANTO  : 'enquanto';
FACA      : 'faca';
PARA      : 'para';
DE        : 'de';
ATE       : 'ate';
PASSO     : 'passo';

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

WS        : [ \t\r\n]+ -> skip;

// -------- REGRAS --------
program
    : (command)* EOF
    ;

command
    : decl
    | assign
    | write
    | enquantoCmd
    | paraCmd
    ;

decl
    : INT ID SEMI
    | BOOLEANO ID SEMI
    | INT ID ABRECOL NUMBER FECHACOL SEMI
    | BOOLEANO ID ABRECOL NUMBER FECHACOL SEMI
    ;

assign
    : ID EQ expr SEMI
    | ID ABRECOL expr FECHACOL EQ expr SEMI   // atribuição em array
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

// -------- EXPRESSÕES --------
// Usando precedência para evitar ambiguidades
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
    | LPAREN expr RPAREN                  # ParenExpr
    ;