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
ELSE : 'else';
IN : 'in';
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
A_ROUND : '@';
IMPLICATION : '=>';

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

TYPE : 'Int' | 'Bool' | 'Float' | 'Object' | 'SELF_TYPE' | CLASS_NAME;

/* identificator */
ID : (LETTER | '_')(LETTER | '_' | DIGIT)*;

INT : DIGIT+;

fragment DIGITS : DIGIT+;
fragment EXPONENT : 'e' ('+' | '-')? DIGITS;
FLOAT : (DIGITS ('.' DIGITS?)? | '.' DIGITS) EXPONENT?;

BOOL : 'true' | 'false';
STRING : '"' ('\\"' | .)*? (  '"'
       | EOF { raiseError("EOF in string constant"); })

    {
        String s = getText();
        s = s.substring(1, s.length() - 1);
        StringBuilder newStr = new StringBuilder();
        int len = s.length();
        int i = 0;

        while (i < len) {
            char ch = s.charAt(i);

            if (ch == '\\') {
                i++;
                char nextCh = s.charAt(i);

                if (nextCh == 'n') {
                    newStr.append('\n');
                } else if (nextCh == 't') {
                    newStr.append('\t');
                } else if (nextCh == 'f') {
                    newStr.append('\f');
                } else if (nextCh == 'b') {
                    newStr.append('\b');
                } else {
                    newStr.append(nextCh);
                }
            } else {
                newStr.append(ch);
            }
            i++;
        }

        setText(newStr.toString());
    }
    ;

WS
    :   [ \n\f\r\t]+ -> skip
    ;
