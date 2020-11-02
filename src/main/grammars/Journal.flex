package nl.jord1e.idea.pta.journal.lang.lexer;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import java.lang.reflect.Field;
import java.util.LinkedList;
import static com.intellij.psi.TokenType.BAD_CHARACTER;
import static com.intellij.psi.TokenType.WHITE_SPACE;
import static nl.jord1e.idea.pta.journal.lang.psi.JournalTypes.*;

%%

%debug
%public
%class JournalLexer
%implements FlexLexer
%function advance
%type IElementType
%unicode

%{
  private final LinkedList<Integer> states = new LinkedList<>();

    private void yypush(int state){
        states.addFirst(yystate());
        yybeginl(state);
    }

    private void yypop() {
        if (states.isEmpty()) {
            yybeginl(YYINITIAL);
        } else {
            final int state = states.removeFirst();
            yybeginl(state);
        }
    }

    private String getStateName(int state) {
        for (Field field : this.getClass().getFields()) {
            if (field.getType() == Integer.TYPE) {
                try {
                    int num = (int) field.get(this);
                    if (num == state) {
                        return field.getName();
                    }
                } catch (IllegalArgumentException | IllegalAccessException ignored) {
                }
            }
        }
        return ":" + state + ":";
    }

    private void yybeginl(int state) {
  //      if (false && state != 0) {
            System.out.println("state: " + getStateName(yystate()) + " -> " + getStateName(state));
  //      }
        yybegin(state);
      }

      private void yypushbackx(int chars) {
        yypushback(yylength() - chars);
      }
%}

EOL=[\n\r]
H_WS=[ ]+
WHITE_SPACE=\s+

STRING=[^\n\r]+
BLOCK_COMMENT_CONTENT=\R([\s\S]*?)\R?
EOL_COMMENT=;.*
POSITIVE_NUMBER=[1-9][0-9]*
NEGATIVE_NUMBER=-[1-9][0-9]*
NUMBER=-?(0|[1-9][0-9]*)(E-?[0-9]+|[.,][0-9]+)?
YEAR=[0-9]{4}
//ACCOUNT_PART=(([a-zA-Z0-9]+( [a-zA-Z0-9]+)*):?)+
IDENTIFIER=[0-9a-zA-Z]
ACCOUNT_PART={IDENTIFIER}([ ]?{IDENTIFIER}+)*
// ([0-9a-zA-Z]([ ]?[0-9a-zA-Z]+)*)
DATE=[0-9]{4}[-/.][0-9]{2}[-/.][0-9]{2}
CURRENCY_SYMBOL=[$¢£¤¥֏؋৲৳৻૱௹฿៛\u20a0-\u20bd\ua838\ufdfc\ufe69\uff04\uffe0\uffe1\uffe5\uffe6]
CURRENCY_NAME=[a-zA-Z]+

%state S_POSTING, S_ACCOUNT, S_CODE, S_BLOCK_COMMENT, S_P, S_D, S_Y, S_ALIAS, S_INCLUDE
%xstate S_TRANSACTION

%%

<S_ACCOUNT> {
  {H_WS}+                              { yypop(); return WHITE_SPACE; }
  {EOL}                                { yypushback(yylength()); yypop(); }
  ":"                                  { return COLON; }
  {ACCOUNT_PART}                       { return ACCOUNT_PART; }
  "("                                  { return LPAREN; }
  ")"                                  { return RPAREN; }
  "["                                  { return LBRACKET; }
  "]"                                  { return RBRACKET; }
}

<S_CODE> {
  [^\r\n)]*                            { return STRING; }
  ")"                                  { yypop(); return RPAREN; }
}

<S_POSTING> {
  {H_WS}+                              { return WHITE_SPACE; }
  {EOL}                                { yypushback(yylength()); yypop(); }
  {NUMBER}                             { return NUMBER; }
  {CURRENCY_SYMBOL}                    { return CURRENCY_SYMBOL; }
  {CURRENCY_NAME}                      { return STRING; }
  "-"                                  { return MINUS; }
  // TODO (2020-11-01/J): Better currency name handling + quotes
  "="                                  { return EQ; }
  "=*"                                 { return EQSTAR; }
  "=="                                 { return EQEQ; }
  "==*"                                { return EQEQSTAR; }
  "@"                                  { return AT; }
  "@@"                                 { return ATAT; }
  "(@)"                                { return ATP; }
  "(@@)"                               { return ATATP; }
}

<S_TRANSACTION> {
//  [^]+ { System.out.println("HERE!"); return IGNORE;  }
  ^[ ]{2}("("|"[")?{ACCOUNT_PART}      { yypushbackx(2); yypush(S_POSTING); yypush(S_ACCOUNT); return WHITE_SPACE; }
  "*"                                  { return STAR; }
  "!"                                  { return EXCLAMATION_MARK; }
  "="                                  { return EQ; }
  "("{STRING}                          { yypushbackx(1); yypush(S_CODE); return LPAREN; }
  {H_WS}                               { return WHITE_SPACE; }
  {DATE}                               { return DATE; }
  {EOL}                                { return EOL; }
  {EOL}{2}                             { yypop(); return EOL; }
  // TODO (2020-11-01/J): This regex sucs (no spaces etc.).
  // https://github.com/simonmichael/hledger/blob/master/hledger-lib/Hledger/Read/Common.hs#L446
  [^ *!:\-\[\](=)\|\r\n]+              { return STRING; }
  "|"                                  { return PIPE; }
}

<S_INCLUDE> {
  {H_WS} { return WHITE_SPACE; }
  [^ ][^\r\n]+ { yypop(); return STRING; }
}

<S_BLOCK_COMMENT> {
  "end comment"                        { yypop(); return BLOCK_COMMENT_END; }
  {BLOCK_COMMENT_CONTENT}              { System.out.println("INDEX:"+yytext().toString().lastIndexOf("end comment")); yypushback(yylength() - yytext().toString().lastIndexOf("end comment")); return BLOCK_COMMENT_CONTENT; }
  {EOL}                                { return EOL; }
}

<S_Y> {
  {YEAR}                               { yypop(); return YEAR; }
}

<S_P> {
  {H_WS}                               { return WHITE_SPACE; }
  {DATE}                               { return DATE; }
  {CURRENCY_SYMBOL}                    { return CURRENCY_SYMBOL; }
  {CURRENCY_NAME}                      { return STRING; }
  {NUMBER}                             { return NUMBER; }
  "-"                                  { return MINUS; } // TODO, neccesariy?
}

<S_D> {
  {CURRENCY_SYMBOL}                    { return CURRENCY_SYMBOL; }
  {CURRENCY_NAME}                      { return STRING; }
  {NUMBER}                             { return NUMBER; }
  {H_WS}                               { return WHITE_SPACE; }
}

<S_ALIAS> {
  {H_WS}                               { return WHITE_SPACE; }
  {ACCOUNT_PART}                       { return ACCOUNT_PART; }
//  [^\\:=\/]+                           { return REGEXP; }
  "/"                                  { return FORWARD_SLASH; }
  ":"                                  { return COLON; }
  "="                                  { return EQ; }
}

<YYINITIAL> {
  {EOL_COMMENT}                        { return EOL_COMMENT; }
  {EOL}                                { return EOL; }
  {H_WS}+                              { return WHITE_SPACE; }
  {DATE}                               { yybeginl(S_TRANSACTION); return DATE; }

//}
//  ","                          { return COMMA; }
//  "\""                         { return DOUBLE_QUOTE; }
//  "-"                          { return MINUS; }
//  "/"                          { return FORWARD_SLASH; }
//  "commodity"                  { return D_COMMODITY; }
//  "account"                    { return D_ACCOUNT; }
//  "alias"                      { return D_ALIAS; }
//  "end aliases"                { return D_END_ALIAS; }
//  "apply account"              { return D_APPLY_ACCOUNT; }
//  "end apply account"          { return D_END_APPLY_ACCOUNT; }
//  "include"                    { return D_INCLUDE; }

  "include"                    { yypush(S_INCLUDE); return D_INCLUDE; }
  "alias"                      { yybeginl(S_ALIAS); return D_ALIAS; }
  "end aliases"                { return D_END_ALIASES; }
  "D"                          { yybeginl(S_D); return D_D; }
  "commodity"                  { yybeginl(S_D); return D_COMMODITY; }
  "P"                          { yybeginl(S_P); return D_P; }
  "Y"                          { yypush(S_Y); return D_Y; }
  "comment"                    { yypush(S_BLOCK_COMMENT); return BLOCK_COMMENT_START; }
}

<S_TRANSACTION, S_ACCOUNT, S_POSTING> {
  [^] { return BAD_CHARACTER; }
}

//          System.out.println("---- Ignore ----\n" + yytext() + "\n----------------");
{EOL} {return EOL;}
[^] { return IGNORE; }

