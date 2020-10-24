package nl.jord1e.hledger.idea.rules

import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.psi.tree.IElementType
import com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey as ctak
import nl.jord1e.hledger.idea.rules.psi.RulesTypes as RT

class RulesSyntaxHighlighter : SyntaxHighlighterBase() {

    private companion object {
        val COMMENT = ctak("RULES_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT)
        val KEYWORD = ctak("RULES_KEYWORD", DefaultLanguageHighlighterColors.KEYWORD)
        val NUMBER = ctak("RULES_NUMBER", DefaultLanguageHighlighterColors.NUMBER)
        val ASSERTIONS = ctak("RULES_ASSERTIONS", DefaultLanguageHighlighterColors.OPERATION_SIGN)
        val VARIABLES = ctak("RULES_VARIABLES", DefaultLanguageHighlighterColors.GLOBAL_VARIABLE)

        val COMMENT_KEYS = arrayOf(COMMENT)
        val KEYWORD_KEYS = arrayOf(KEYWORD)
        val NUMBER_KEYS = arrayOf(NUMBER)
        val EMPTY_KEYS = emptyArray<TextAttributesKey>()
        val OPERATOR_KEYS = arrayOf(ASSERTIONS)
        val VARIABLE_KEYS = arrayOf(VARIABLES)
    }

    override fun getHighlightingLexer() = RulesLexerAdapter()

    override fun getTokenHighlights(tokenType: IElementType) = when (tokenType) {
        RT.COMMENT -> COMMENT_KEYS
        RT.D_BALANCE_TYPE, RT.D_DATE_FORMAT, RT.D_INCLUDE,
        RT.D_FIELDS, RT.D_SKIP, RT.D_NEWEST_FIRST,
        RT.D_SEPARATOR, RT.D_IF -> KEYWORD_KEYS
        RT.CSV_FIELD -> VARIABLE_KEYS
        RT.DIGIT -> NUMBER_KEYS
        RT.BALANCE_ASSERTION_TYPE -> OPERATOR_KEYS
        else -> EMPTY_KEYS
    }

}
