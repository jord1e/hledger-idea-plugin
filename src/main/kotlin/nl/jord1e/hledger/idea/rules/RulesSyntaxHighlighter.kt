package nl.jord1e.hledger.idea.rules

import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.psi.tree.IElementType
import nl.jord1e.hledger.idea.rules.psi.RulesTypes
import com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey as ctak

class RulesSyntaxHighlighter : SyntaxHighlighterBase() {

    private companion object {
        val COMMENT = ctak("RULES_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT)
        val KEYWORD = ctak("RULES_KEYWORD", DefaultLanguageHighlighterColors.KEYWORD)
        val NUMBER = ctak("RULES_NUMBER", DefaultLanguageHighlighterColors.NUMBER)
        val ASSERTIONS = ctak("RULES_ASSERTIONS", DefaultLanguageHighlighterColors.OPERATION_SIGN)

        val COMMENT_KEYS = arrayOf(COMMENT)
        val KEYWORD_KEYS = arrayOf(KEYWORD)
        val NUMBER_KEYS = arrayOf(NUMBER)
        val EMPTY_KEYS = emptyArray<TextAttributesKey>()
        val OPERATOR_KEYS = arrayOf(ASSERTIONS)
    }

    override fun getHighlightingLexer() = RulesLexerAdapter()

    override fun getTokenHighlights(tokenType: IElementType) = when (tokenType) {
        RulesTypes.COMMENT -> COMMENT_KEYS
        RulesTypes.BALANCE_TYPE, RulesTypes.DATE_FORMAT, RulesTypes.INCLUDE,
        RulesTypes.FIELDS, RulesTypes.SKIP, RulesTypes.NEWEST_FIRST -> KEYWORD_KEYS
        RulesTypes.NUMBER -> NUMBER_KEYS
        RulesTypes.BALANCE_ASSERTION_TYPE -> OPERATOR_KEYS
        else -> EMPTY_KEYS
    }

}
