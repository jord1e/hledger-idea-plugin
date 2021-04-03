package nl.jord1e.idea.pta.journal.parser;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;

import static com.intellij.psi.TokenType.BAD_CHARACTER;
import static com.intellij.psi.TokenType.WHITE_SPACE;
import static nl.jord1e.idea.pta.journal.lang.psi.JournalTypes.*;

%%

%{
  public _JournalLexer() {
    this((java.io.Reader)null);
  }
%}

%public
%class _JournalLexer
%implements FlexLexer
%function advance
%type IElementType
%unicode

EOL=\R
WHITE_SPACE=\s+

STRING=[^\n\r]+
BLOCK_COMMENT_CONTENT=\R([\s\S]*?)\R?
EOL_COMMENT=;.*$
POSITIVE_NUMBER=^[1-9][0-9]*$
NEGATIVE_NUMBER=^-[1-9][0-9]*$
NUMBER=(0|[1-9][0-9]*)(E-?[0-9]+|[.,][0-9]+)
ACCOUNT_NAME=^(([a-zA-Z0-9]+( [a-zA-Z0-9]+)*):?)+$
DATE=[0-9]{4}[-/.][0-9]{2}[-/.][0-9]{2}
CURRENCY_SYMBOL=[$¢£¤¥֏؋৲৳৻૱௹฿៛\u20a0-\u20bd\ua838\ufdfc\ufe69\uff04\uffe0\uffe1\uffe5\uffe6]

%%
<YYINITIAL> {
  {WHITE_SPACE}                { return WHITE_SPACE; }

  "("                          { return LPAREN; }
  ")"                          { return RPAREN; }
  "["                          { return LBRACKET; }
  "]"                          { return RBRACKET; }
  "{"                          { return LBRACE; }
  "}"                          { return RBRACE; }
  "|"                          { return PIPE; }
  "*"                          { return STAR; }
  ","                          { return COMMA; }
  "!"                          { return EXCLAMATION_MARK; }
  ":"                          { return COLON; }
  "\""                         { return DOUBLE_QUOTE; }
  "-"                          { return MINUS; }
  "/"                          { return FORWARD_SLASH; }
  "@"                          { return AT; }
  "@@"                         { return ATAT; }
  "(@)"                        { return ATP; }
  "(@@)"                       { return ATATP; }
  "="                          { return EQ; }
  "=*"                         { return EQSTAR; }
  "=="                         { return EQEQ; }
  "==*"                        { return EQEQSTART; }
  "commodity"                  { return D_COMMODITY; }
  "account"                    { return D_ACCOUNT; }
  "alias"                      { return D_ALIAS; }
  "end aliases"                { return D_END_ALIAS; }
  "apply account"              { return D_APPLY_ACCOUNT; }
  "end apply account"          { return D_END_APPLY_ACCOUNT; }
  "D"                          { return D_D; }
  "include"                    { return D_INCLUDE; }
  "P"                          { return D_P; }
  "Y"                          { return D_Y; }
  "regexpp:[a-zA-Z0-9-]+"      { return TAG_NAME; }
  "regexpp:[^:\\R,]"           { return TAG_VALUE; }
  "regexpp:^[;#*].*$"          { return LINE_COMMENT; }
  "comment"                    { return BLOCK_COMMENT_START; }
  "end comment"                { return BLOCK_COMMENT_END; }
  "E"                          { return SCIENTIFC_E; }

  {STRING}                     { return STRING; }
  {BLOCK_COMMENT_CONTENT}      { return BLOCK_COMMENT_CONTENT; }
  {EOL_COMMENT}                { return EOL_COMMENT; }
  {POSITIVE_NUMBER}            { return POSITIVE_NUMBER; }
  {NEGATIVE_NUMBER}            { return NEGATIVE_NUMBER; }
  {NUMBER}                     { return NUMBER; }
  {ACCOUNT_NAME}               { return ACCOUNT_NAME; }
  {DATE}                       { return DATE; }
  {CURRENCY_SYMBOL}            { return CURRENCY_SYMBOL; }

}

[^] { return BAD_CHARACTER; }
