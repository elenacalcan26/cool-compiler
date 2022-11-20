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
    : formalDef=formal (ASSIGN val=expr) # varDef
    ;

formal
    : name=ID COLON type=TYPE
    ;

expr
    : name=ID ASSIGN expr   # assignment
    | ID    # id
    | INT   # int
    | BOOL  # bool
    ;
