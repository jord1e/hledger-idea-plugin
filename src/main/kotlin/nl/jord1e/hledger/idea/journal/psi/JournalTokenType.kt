package nl.jord1e.hledger.idea.journal.psi

import com.intellij.psi.tree.IElementType
import nl.jord1e.hledger.idea.journal.JournalLanguage

class JournalTokenType(debugName: String) : IElementType(debugName, JournalLanguage) {

    override fun toString() = "JournalToken:" + super.toString()

}
