grammar EasyLanguage;

// -------- TOKENS --------
INT     : 'int';
WRITE   : 'escreva';
ENQUANTO: 'enquanto';
FACA    : 'faca';
PARA    : 'para';
DE      : 'de';
ATE     : 'ate';
PASSO   : 'passo';

ID      : [a-zA-Z_][a-zA-Z0-9_]*;
NUMBER  : [0-9]+;
SEMI    : ';';
EQ      : '=';
LPAREN  : '(';
RPAREN  : ')';
WS      : [ \t\r\n]+ -> skip;

// -------- REGRAS --------
program     : (command)* EOF ;

command
    : decl
    | assign
    | write
    | enquantoCmd
    | paraCmd
    ;

decl        : INT ID SEMI ;
assign      : ID EQ expr SEMI ;
write       : WRITE LPAREN ID RPAREN SEMI ;

enquantoCmd
    : ENQUANTO LPAREN expr RPAREN FACA command*
    ;

paraCmd
    : PARA ID DE expr ATE expr (PASSO expr)? FACA command*
    ;

// -------- EXPRESSÕES (simples por enquanto) --------
expr
    : NUMBER
    | ID
    ;