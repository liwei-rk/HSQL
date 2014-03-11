lexer grammar HSQLLexer;
@header{
package org.ict.hsql.parse;
import org.ict.hsql.schema.*;
}
SUM:'SUM';
CREATE:'CREATE';
ON:'ON';
INDEX:'INDEX';
DELETE:'DELETE';
WHERE:'WHERE';
SELECT:'SELECT';
FROM:'FROM';
DROP:'DROP';
INSERT:'INSERT';
UPDATE:'UPDATE';
INTO:'INTO';
VALUES:'VALUES';
TABLE:'TABLE';
SET:'SET';
OR:'OR';
AND:'AND';
PRIMARYKEY:'PRIMARY KEY';
INT:'INT';
STRING:'STRING';
DOUBLE:'DOUBLE';
LONG:'LONG';
STR:('a'..'z'|'A'..'Z'|'_')('a'..'z'|'A'..'Z'|'_'|':'|'0'..'9')*;
WS:(' '|'\t')+ {$channel=HIDDEN;};
L_PAREN:'(';
R_PAREN:')';
COMMA:',';
MINUS:'-';

STR_LITERALS:'\''STR;
fragment
LETTER
    :    'a'..'z'
    |    'A'..'Z'
    ;

// string literals
STRING_LITERAL
@init{ StringBuilder sb = new StringBuilder(); }
    :   '\''
    ( t=CHAR { sb.append(t.getText()); }
    | t=CHAR_ESC { sb.append(getText()); }
    )* '\'' { setText(sb.toString()); }
    ;

fragment
CHAR
    :   ( ~('\'' | '\\') )+     //not ' or \'
    ;

fragment
DBL_QUOTE_CHAR
    :   ( ~('\"') )+     //char quoted in "
    ;

// escape sequence inside a string literal
fragment
CHAR_ESC
    :   '\\'     //in literal,tel the \ is not escape flag in lexer construction
        ( 'n'   { setText("\n"); }
        | 'r'   { setText("\r"); }
        | 't'   { setText("\t"); }
        | 'b'   { setText("\b"); }
        | 'f'   { setText("\f"); }
        | '\"'  { setText("\""); }
        | '\''  { setText("\'"); }
        | '\\'  { setText("\\"); }
        | '_'   { setText("\\_"); }
        | '%'   { setText("\\\%"); }
        )
    |   '\'\''  { setText("\'"); }        //too consequece '' is set to an \' char
    ;
NEQ
    :'!='
    ;
EQ
    : '='
    ;
LT
    :'<'
    ;
LTE
    :'<='
    ;
GT
    :'>'
    ;
GTE
    :'>='
    ;

EOL
    :  ('\r' | '\n')
    { skip(); }
    ;

DOT
    : '.'
    ;


fragment
POSINTEGER
    :   DIGIT+
    ;
fragment
DIGIT
    :    '0'..'9'
    ;
INTEGER_LITERAL
    :MINUS?POSINTEGER
    ;

LONG_LITERAL
    :MINUS?POSINTEGER ('L'|'l')
    ;
DOUBLE_LITERAL
    :MINUS?DECIMAL ('D'|'d')?
    ;
// Exponential format is not supported.
fragment
DECIMAL
    :   POSINTEGER? '.' POSINTEGER
    ;