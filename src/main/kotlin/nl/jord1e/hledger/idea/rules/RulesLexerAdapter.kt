package nl.jord1e.hledger.idea.rules

import com.intellij.lexer.FlexAdapter

class RulesLexerAdapter : FlexAdapter(RulesLexer(null)) {
}
