@file:Suppress("UnusedImport")

package nl.jord1e.hledger.idea.rules

import com.intellij.lexer.FlexAdapter
import nl.jord1e.hledger.idea.rules.RulesLexer

class RulesLexerAdapter : FlexAdapter(RulesLexer(null)) {
}
