package nl.jord1e.idea.pta.rules.lang.psi

import com.intellij.psi.tree.IElementType
import nl.jord1e.idea.pta.rules.lang.RulesLanguage

class RulesTokenType(debugName: String) : IElementType(debugName, RulesLanguage) {

    override fun toString() = "RulesToken:" + super.toString()

}
