package nl.jord1e.hledger.idea.rules.psi

import com.intellij.psi.tree.IElementType
import nl.jord1e.hledger.idea.rules.RulesLanguage

class RulesTokenType(debugName: String) : IElementType(debugName, RulesLanguage) {

    override fun toString() = "RulesToken:" + super.toString()

}
