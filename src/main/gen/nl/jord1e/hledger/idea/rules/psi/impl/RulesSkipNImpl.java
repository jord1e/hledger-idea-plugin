// This is a generated file. Not intended for manual editing.
package nl.jord1e.hledger.idea.rules.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static nl.jord1e.hledger.idea.rules.psi.RulesTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import nl.jord1e.hledger.idea.rules.psi.*;

public class RulesSkipNImpl extends ASTWrapperPsiElement implements RulesSkipN {

  public RulesSkipNImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull RulesVisitor visitor) {
    visitor.visitSkipN(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof RulesVisitor) accept((RulesVisitor)visitor);
    else super.accept(visitor);
  }

}
