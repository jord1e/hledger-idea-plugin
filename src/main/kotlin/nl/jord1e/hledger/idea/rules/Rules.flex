package nl.jord1e.hledger.idea.rules;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.TokenType;
import nl.jord1e.hledger.idea.rules.psi.RulesTypes;

%%

%class RulesLexer
%implements FlexLexer
%unicode
%function advance
%type IElementType
%eof{  return;
%eof}

CRLF=\R
WHITE_SPACE=[\ \n\t\f]
SPACE = " "
FIRST_VALUE_CHARACTER=[^ \n\f\\] | "\\"{CRLF} | "\\".
VALUE_CHARACTER=[^\n\f\\] | "\\"{CRLF} | "\\".
COMMENT=("#"|";")[^\r\n]*
SEPARATOR=[:=]
KEY_CHARACTER=[^:=\ \n\t\f\\] | "\\ "
NUMBER = [0-9]

SKIP = "skip"
//FIELDS = "fields"
//SEPERATOR = "seperator"
//END = "end"
//DATE_FORMAT = "date-format"
//NEWEST_FIRST = "newest-first"
//INCLUDE = "include"
BALANCE_TYPE = "balance-type"

BALANCE_ASSERTION_SINGLE_EXCLUDE = "="
BALANCE_ASSERTION_SINGLE_INCLUDE = "=*"
BALANCE_ASSERTION_MULTI_EXCLUDE = "=="
BALANCE_ASSERTION_MULTI_INCLUDE = "==*"
BALANCE_ASSERTION_TYPE = {BALANCE_ASSERTION_SINGLE_EXCLUDE} |
                         {BALANCE_ASSERTION_SINGLE_INCLUDE} |
                         {BALANCE_ASSERTION_MULTI_EXCLUDE} |
                         {BALANCE_ASSERTION_MULTI_INCLUDE}


%state WAITING_VALUE

%%

<YYINITIAL> {COMMENT}                                       { yybegin(YYINITIAL); return RulesTypes.COMMENT; }
// todo 1
<YYINITIAL> {
    {SKIP} { yybegin(YYINITIAL); return RulesTypes.SKIP; }
    {SPACE} { yybegin(YYINITIAL); return TokenType.WHITE_SPACE; }
    {NUMBER}+ { yybegin(YYINITIAL); return RulesTypes.NUMBER; }
    {BALANCE_TYPE} { yybegin(YYINITIAL); return RulesTypes.BALANCE_TYPE; }
    {BALANCE_ASSERTION_TYPE} { yybegin(YYINITIAL); return RulesTypes.BALANCE_ASSERTION_TYPE; }
}



//<YYINITIAL> {KEY_CHARACTER}+                                { yybegin(YYINITIAL); return RulesTypes.KEY; }
//
//<YYINITIAL> {SEPARATOR}                                     { yybegin(WAITING_VALUE); return RulesTypes.SEPARATOR; }
//
//<WAITING_VALUE> {CRLF}({CRLF}|{WHITE_SPACE})+               { yybegin(YYINITIAL); return TokenType.WHITE_SPACE; }
//
//<WAITING_VALUE> {WHITE_SPACE}+                              { yybegin(WAITING_VALUE); return TokenType.WHITE_SPACE; }
//
//<WAITING_VALUE> {FIRST_VALUE_CHARACTER}{VALUE_CHARACTER}*   { yybegin(YYINITIAL); return RulesTypes.VALUE; }

({CRLF}|{WHITE_SPACE})+                                     { yybegin(YYINITIAL); return TokenType.WHITE_SPACE; }

[^]                                                         { return TokenType.BAD_CHARACTER; }
