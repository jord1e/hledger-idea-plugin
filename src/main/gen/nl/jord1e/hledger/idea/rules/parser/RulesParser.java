// This is a generated file. Not intended for manual editing.
package nl.jord1e.hledger.idea.rules.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import static nl.jord1e.hledger.idea.rules.psi.RulesTypes.*;
import static com.intellij.lang.parser.GeneratedParserUtilBase.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.PsiParser;
import com.intellij.lang.LightPsiParser;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class RulesParser implements PsiParser, LightPsiParser {

  public ASTNode parse(IElementType root_, PsiBuilder builder_) {
    parseLight(root_, builder_);
    return builder_.getTreeBuilt();
  }

  public void parseLight(IElementType root_, PsiBuilder builder_) {
    boolean result_;
    builder_ = adapt_builder_(root_, builder_, this, null);
    Marker marker_ = enter_section_(builder_, 0, _COLLAPSE_, null);
    result_ = parse_root_(root_, builder_);
    exit_section_(builder_, 0, marker_, root_, result_, true, TRUE_CONDITION);
  }

  protected boolean parse_root_(IElementType root_, PsiBuilder builder_) {
    return parse_root_(root_, builder_, 0);
  }

  static boolean parse_root_(IElementType root_, PsiBuilder builder_, int level_) {
    return simpleFile(builder_, level_ + 1);
  }

  /* ********************************************************** */
  // BALANCE_TYPE BALANCE_ASSERTION
  public static boolean balanceExp(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "balanceExp")) return false;
    if (!nextTokenIs(builder_, BALANCE_TYPE)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, BALANCE_TYPE, BALANCE_ASSERTION);
    exit_section_(builder_, marker_, BALANCE_EXP, result_);
    return result_;
  }

  /* ********************************************************** */
  // skipN|balanceExp|newestFirstExp|COMMENT|CRLF
  static boolean item_(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "item_")) return false;
    boolean result_;
    result_ = skipN(builder_, level_ + 1);
    if (!result_) result_ = balanceExp(builder_, level_ + 1);
    if (!result_) result_ = newestFirstExp(builder_, level_ + 1);
    if (!result_) result_ = consumeToken(builder_, COMMENT);
    if (!result_) result_ = consumeToken(builder_, CRLF);
    return result_;
  }

  /* ********************************************************** */
  // NEWEST_FIRST
  static boolean newestFirstExp(PsiBuilder builder_, int level_) {
    return consumeToken(builder_, NEWEST_FIRST);
  }

  /* ********************************************************** */
  // item_*
  static boolean simpleFile(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "simpleFile")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!item_(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "simpleFile", pos_)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // SKIP NUMBER | SKIP
  public static boolean skipN(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "skipN")) return false;
    if (!nextTokenIs(builder_, SKIP)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = parseTokens(builder_, 0, SKIP, NUMBER);
    if (!result_) result_ = consumeToken(builder_, SKIP);
    exit_section_(builder_, marker_, SKIP_N, result_);
    return result_;
  }

}
