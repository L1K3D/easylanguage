# Gramática Formal da Linguagem .easy (EBNF)

A seguir estão listadas as regras sintáticas básicas da linguagem .easy, utilizando a notação EBNF (Extended Backus-Naur Form):

```ebnf
program         = { statement } ;

statement       = assignment
                | if_statement
                | while_statement
                | function_def
                | function_call
                | print_statement
                ;

assignment      = "let", identifier, "=", expression, ";" ;

if_statement    = "if", "(", expression, ")", block, [ "else", block ] ;

while_statement = "while", "(", expression, ")", block ;

function_def    = "fn", identifier, "(", [ param_list ], ")", block ;

function_call   = identifier, "(", [ arg_list ], ")", ";" ;

print_statement = "print", "(", expression, ")", ";" ;

block           = "{", { statement }, "}" ;

param_list      = identifier, { ",", identifier } ;

arg_list        = expression, { ",", expression } ;

expression      = logical_or ;

logical_or      = logical_and, { "||", logical_and } ;

logical_and     = equality, { "&&", equality } ;

equality        = relational, { ("==" | "!="), relational } ;

relational      = addition, { ("<" | ">" | "<=" | ">="), addition } ;

addition        = multiplication, { ("+" | "-"), multiplication } ;

multiplication  = unary, { ("*" | "/"), unary } ;

unary           = [ ("!" | "-") ], primary ;

primary         = number
                | string
                | identifier
                | "true"
                | "false"
                | "(", expression, ")"
                ;

identifier      = letter, { letter | digit | "_" } ;

number          = digit, { digit } ;

string          = '"', { any_character_except_quote }, '"' ;

letter          = "a" | "b" | ... | "z" | "A" | "B" | ... | "Z" ;

digit           = "0" | "1" | "2" | "3" | "4" | "5" | "6" | "7" | "8" | "9" ;
```