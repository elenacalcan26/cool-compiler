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
    : name=ID COLON type=TYPE (ASSIGN val=expr)? # varDef
    ;

formal
    : name=ID COLON type=TYPE
    ;

expr
    : name=ID ASSIGN args=expr   # assign
    | ID    # id
    | INT   # int
    | BOOL  # bool
    ;
