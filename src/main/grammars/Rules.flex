package nl.jord1e.hledger.idea.rules;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import static com.intellij.psi.TokenType.BAD_CHARACTER;
import static com.intellij.psi.TokenType.WHITE_SPACE;
import static nl.jord1e.hledger.idea.rules.psi.RulesTypes.*;

%%

%{
  public RulesLexer() {
    this((java.io.Reader)null);
  }
%}

%public
%class RulesLexer
%implements FlexLexer
%function advance
%type IElementType
%unicode

EOL=\R
WHITE_SPACE=\s+

DIGIT=[0-9]
NUMBER={DIGIT}+
SPACE_CHAR=[ \t]
BLANK={SPACE_CHAR}+
CHAR=[^\n]
COMMENT=[#;] [^\r\n]*
BALANCE_ASSERTION_TYPE===?\*?
ANY=[^]

%state G_BALANCE_TYPE
%state G_SKIP

%%
<YYINITIAL> {
  {EOL}                         { return NEWLINE; }
  {WHITE_SPACE}                 { return WHITE_SPACE; }

  "regexp"                      { return REGEXP; }
  "skip"                        { yybegin(G_SKIP); return D_SKIP; }
  "fields"                      { return D_FIELDS; }
  "separator"                   { return D_SEPARATOR; }
  "end"                         { return D_END; }
  "date-format"                 { return D_DATE_FORMAT; }
  "newest-first"                { return D_NEWEST_FIRST; }
  "include"                     { return D_INCLUDE; }
  "balance-type"                { yybegin(G_BALANCE_TYPE); return D_BALANCE_TYPE; }
  "if"                          { return D_IF; }

  "%"                           { return PERCENT; }
  "&"                           { return AND; }
  "\""                          { return QUOTE; }
  "~"                           { return TILDE; }

  {NUMBER}                      { return NUMBER; }
  {CHAR}                        { return CHAR; }
  {COMMENT}                     { return COMMENT; }
  {BALANCE_ASSERTION_TYPE}      { return BALANCE_ASSERTION_TYPE; }
}

<G_BALANCE_TYPE> {
  {WHITE_SPACE}                 { return WHITE_SPACE; }
  {BALANCE_ASSERTION_TYPE}      { return BALANCE_ASSERTION_TYPE; }
}

<G_SKIP> {
  {WHITE_SPACE}                 { return WHITE_SPACE; }
  {NUMBER}                      { return NUMBER; }
}

{ANY} { return BAD_CHARACTER; }
