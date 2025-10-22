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
expr
    : NUMBER
    | VERDADEIRO
    | FALSO
    | ID
    | ID ABRECOL expr FECHACOL          // acesso a array
    | expr MENOR expr                   // menor que
    | expr MAIOR expr                   // maior que
    | expr IGUAL expr                   // igual a
    | expr DIFERENTE expr               // diferente de
    | expr E expr                       // operador lógico E
    | expr OU expr                      // operador lógico OU
    | NAO expr                          // operador lógico NAO
    | LPAREN expr RPAREN                // parênteses
    ;