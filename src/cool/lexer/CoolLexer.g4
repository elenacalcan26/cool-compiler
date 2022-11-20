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
fragment CLASS_NAME : UPPER_LETTER (LETTER | DIGIT)*;

TYPE : 'Int' | 'Bool' | 'Float' | 'SELF_TYPE' | CLASS_NAME;

/* identificator */
ID : (LETTER | '_')(LETTER | '_' | DIGIT)*;

INT : DIGIT+;

fragment DIGITS : DIGIT+;
fragment EXPONENT : 'e' ('+' | '-')? DIGITS;
FLOAT : (DIGITS ('.' DIGITS?)? | '.' DIGITS) EXPONENT?;

WS
    :   [ \n\f\r\t]+ -> skip
    ; 