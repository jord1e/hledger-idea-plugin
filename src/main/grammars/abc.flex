package nl.jord1e.idea.pta.rules;

import com.intellij.psi.tree.IElementType;
import static com.intellij.psi.TokenType.BAD_CHARACTER;
import static com.intellij.psi.TokenType.WHITE_SPACE;
import static nl.jord1e.idea.pta.rules.lang.psi.RulesTypes.*;

%%

%public
%class RulesLexer
%implements FlexLexer
%function advance
%type IElementType
%unicode

%{
  StringBuilder string = new StringBuilder();
//  public RulesLexer() {
//    this((java.io.Reader)null);
//  }

  protected void yybeginl(int state) {
      if (false && state != 0) {
        System.out.println("Switching to state: " + state);
      }
      yybegin(state);
  }
%}

V_WS=(\r|\n|\r\n) // \R
H_WS=[\ \t\f]+
//WHITE_SPACE=({H_WS}|{V_WS})+

DIGIT=[0-9]
NUMBER={DIGIT}+
SPACE=" "
STRING=[^\  \t\f\n\r\t]+

COMMENT=[#;][^\n\r]*
//QUOTE=\"
COMMA=","
BALANCE_ASSERTION_TYPE===?\*?
//STRING_X=(\"([^\"\\]|\\.)*\")

FIELD_QUOTED=(\"[^\r\n\"]*\"?)
//FIELD_BARE=[^\"\t\n,;#~]+(?=([^"]*"[^"]*")*[^"]*\Z)
FIELD_BARE=[^\" \t\n,;#~]+
//FIELD_REGEX=
ANY=[^]

%state S_BALANCE_TYPE
%state S_SKIP
%state S_SEPARATOR
%state S_FIELD
%state S_FIELD_REC
%state S_FIELD_NAME
%state S_FIELD_NAME_QUOTED
%state S_IF
%state S_IF_CSVFIELD
%state S_DATE_FORMAT
%state S_MATCHER_SINGLE
%state S_MATCHER_MULTI
%state S_INCLUDE
%state S_MATCHER_REGEX

%%

<YYINITIAL> {
  {COMMENT}                     { return COMMENT; }

  "skip"                        { yybeginl(S_SKIP); return D_SKIP; }
  "fields"                      { yybeginl(S_FIELD); return D_FIELDS; }
  "separator"                   { yybegin(S_SEPARATOR); return D_SEPARATOR; }
  "date-format"                 { yybegin(S_DATE_FORMAT); return D_DATE_FORMAT; }
  "newest-first"                { return D_NEWEST_FIRST; }
  "balance-type"                { yybegin(S_BALANCE_TYPE); return D_BALANCE_TYPE; }
  "if"                          { yybegin(S_IF); return D_IF; }
  "include"                     { yybeginl(S_INCLUDE); return D_INCLUDE; }
}

<S_BALANCE_TYPE> {
  {H_WS}                        { return WHITE_SPACE; }
  {BALANCE_ASSERTION_TYPE}      { return BALANCE_ASSERTION_TYPE; }
}

<S_SKIP> {
  {H_WS}                        { return WHITE_SPACE; }
  {NUMBER}                      { return NUMBER; }
}

<S_SEPARATOR> {
  {H_WS}                        { return WHITE_SPACE; }
  {STRING}                      { return STRING; }
}

// todo: some fancy haskell date format thing
<S_DATE_FORMAT> {
  {H_WS}                        { return WHITE_SPACE; }
  {STRING}                      { return DATEFMT; }
}

<S_FIELD> {
  {H_WS}                        { yybeginl(S_FIELD_REC); return WHITE_SPACE; }
}

<S_FIELD_REC> {
  {FIELD_BARE}                  { return STRING; }
  {FIELD_QUOTED}                { return STRING; }
  {COMMA}                       { yybeginl(S_FIELD_REC); return COMMA; }
}


<S_IF> {
  "end"                         { return D_END; }
  {SPACE}                       { yybeginl(S_MATCHER_SINGLE); return WHITE_SPACE; }
  {V_WS}                        { yybeginl(S_MATCHER_MULTI); return EOL; }
}

<S_MATCHER_SINGLE> {
  %[^\r\n]+                     { yypushback(yylength() - 1); yybeginl(S_IF_CSVFIELD); return PERCENT; }
  [^\r\n]+                      { return REGEX; } // todo: leading whitespace
}

<S_MATCHER_MULTI> {
  %[^\r\n]+                     { yypushback(yylength() - 1); yybeginl(S_IF_CSVFIELD); return PERCENT; }
  &[^\r\n]+                     { yypushback(yylength() - 1); yybeginl(S_MATCHER_MULTI); return AND; }
  [^\r\n]+                      { return REGEX; }
  {V_WS}                        { yybeginl(S_MATCHER_MULTI); return EOL; }
}

<S_IF_CSVFIELD> {
  {NUMBER}                      { yybeginl(S_MATCHER_REGEX); return NUMBER; }
  {FIELD_BARE}                  { yybeginl(S_MATCHER_REGEX); return STRING; }
  {FIELD_QUOTED}                { yybeginl(S_MATCHER_REGEX); return STRING; }
}

<S_MATCHER_REGEX> {
  [^\r\n]+                      { return REGEX; }
}

{H_WS}                          { return WHITE_SPACE; }
{V_WS}                          { yybeginl(YYINITIAL); return EOL; }
{ANY}                           { return BAD_CHARACTER; }
