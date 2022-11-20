lexer grammar CoolLexer;

tokens { ERROR } 

@header{
    package cool.lexer;	
}

@members{    
    private void raiseError(String msg) {
        setText(msg);
        setType(ERROR);
    }
}

/* cuvinte cheie */
CLASS : 'class';
IF : 'if';
FI : 'fi';
IN : 'IN';
INHERITS : 'inherits';
ISVOID : 'isvoid';
LET : 'let';
LOOP : 'loop';
POOL : 'pool';
THEN : 'then';
WHILE : 'while';
CASE : 'case';
ESAC : 'esac';
NEW : 'new';
OF : 'of';
NOT : 'not';

/* simboluri */

LPAREN : '(';
RPAREN : ')';
LBRACE : '{';
RBRACE : '}';
COLON : ':';
SEMICOLON : ';';
COMMA : ',';
DOT : '.';
NEG : '~';
ASSIGN : '<-';

/* operatori aritmetici */
PLUS : '+';
MINUS : '-';
MUL : '*';
DIV : '/';

/* operatori comparatie */
EQUAL : '=';
LT : '<';
LE : '<=';

fragment DIGIT : [0-9];
fragment LETTER : [a-zA-Z];
fragment UPPER_LETTER : [A-Z];

TYPE : UPPER_LETTER;

WS
    :   [ \n\f\r\t]+ -> skip
    ; 