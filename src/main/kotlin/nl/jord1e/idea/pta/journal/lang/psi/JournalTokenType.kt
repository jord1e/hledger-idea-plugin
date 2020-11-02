package nl.jord1e.idea.pta.journal.lang.psi

import com.intellij.psi.tree.IElementType
import nl.jord1e.idea.pta.rules.lang.RulesLanguage

class JournalTokenType(debugName: String) : IElementType(debugName, RulesLanguage) {

    override fun toString() = "JournalToken:" + super.toString()

}

