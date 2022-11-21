parser grammar CoolParser;

options {
    tokenVocab = CoolLexer;
}

@headerx{
    package cool.parser;
}

program
    : (class_def SEMICOLON)+
    ;

class_def
    : CLASS className=TYPE (INHERITS inheritedClass=TYPE)? LBRACE (feature SEMICOLON)* RBRACE
    ;

feature
    : funcName=ID LPAREN (formals+=formal (COMMA formals+=formal)*)? RPAREN COLON funcType=TYPE
            LBRACE body=expr RBRACE # funcDef
    | name=ID COLON type=TYPE (ASSIGN val=expr)? # varDef
    ;

formal
    : name=ID COLON type=TYPE
    ;

expr
    : name=ID op=ASSIGN args=expr   # assign
    | caller=expr (A_ROUND callType=TYPE)? DOT funcName=ID LPAREN (args+=expr (COMMA args+=expr)*)? RPAREN    # explicitDispatch
    | funcName=ID LPAREN (args+=expr (COMMA args+=expr)*)? RPAREN                  # implicitDispatch
    | op=NEW type=TYPE                  # new
    | op=ISVOID expression=expr                # isvoid
    | leftOp=expr op=(MUL | DIV) rightOp=expr # mulDiv
    | leftOp=expr op=(PLUS | MINUS) rightOp=expr # plusMinus
    | op=NEG rightOp=expr      # negate
    | leftOp=expr op=(LT | LE | EQUAL) rightOp=expr    # comparison
    | op=NOT expression=expr                        # not
    | LPAREN expression=expr RPAREN        # paren
    | ID    # id
    | INT   # int
    | STRING # string
    | BOOL  # bool
    ;
