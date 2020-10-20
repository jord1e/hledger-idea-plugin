// This is a generated file. Not intended for manual editing.
package nl.jord1e.hledger.idea.rules.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import nl.jord1e.hledger.idea.rules.psi.impl.*;

public interface RulesTypes {

  IElementType BALANCE_EXP = new RulesElementType("BALANCE_EXP");
  IElementType SKIP_N = new RulesElementType("SKIP_N");

  IElementType BALANCE_ASSERTION = new RulesTokenType("BALANCE_ASSERTION");
  IElementType BALANCE_ASSERTION_TYPE = new RulesTokenType("= / =* / == / ==*");
  IElementType BALANCE_TYPE = new RulesTokenType("balance-type");
  IElementType COMMENT = new RulesTokenType("COMMENT");
  IElementType CRLF = new RulesTokenType("CRLF");
  IElementType DATE_FORMAT = new RulesTokenType("date-format");
  IElementType END = new RulesTokenType("End");
  IElementType FIELDS = new RulesTokenType("Fields");
  IElementType INCLUDE = new RulesTokenType("Include");
  IElementType NEWEST_FIRST = new RulesTokenType("newest-first");
  IElementType NUMBER = new RulesTokenType("Number");
  IElementType SEPERATOR = new RulesTokenType("Seperator");
  IElementType SKIP = new RulesTokenType("Skip");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == BALANCE_EXP) {
        return new RulesBalanceExpImpl(node);
      }
      else if (type == SKIP_N) {
        return new RulesSkipNImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
