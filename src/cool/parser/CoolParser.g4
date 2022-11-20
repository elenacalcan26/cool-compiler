parser grammar CoolParser;

options {
    tokenVocab = CoolLexer;
}

@headerx{
    package cool.parser;
}

program
    : (class_def SEMICOLON)+  EOF
    ;

class_def
    : CLASS className=TYPE (INHERITS inheritedClass=TYPE)? LBRACE RBRACE
    ;
