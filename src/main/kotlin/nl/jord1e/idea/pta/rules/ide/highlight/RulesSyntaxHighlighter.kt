package nl.jord1e.idea.pta.rules.ide.highlight

import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.psi.tree.IElementType
import nl.jord1e.idea.pta.rules.lang.lexer.RulesLexerAdapter
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors as DLHC
import com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey as ctak
import nl.jord1e.idea.pta.rules.lang.psi.RulesTypes as RT

class RulesSyntaxHighlighter : SyntaxHighlighterBase() {

    private companion object {
        val COMMENT = ctak("RULES_COMMENT", DLHC.LINE_COMMENT)
        val KEYWORD = ctak("RULES_KEYWORD", DLHC.KEYWORD)
        val NUMBER = ctak("RULES_NUMBER", DLHC.NUMBER)
        val ASSERTIONS = ctak("RULES_ASSERTIONS", DLHC.OPERATION_SIGN)
        val OPERATORS = ctak("RULES_OPERATORS", DLHC.OPERATION_SIGN)
        val VARIABLES = ctak("RULES_VARIABLES", DLHC.KEYWORD)
        val COMMA = ctak("RULES_COMMA", DLHC.COMMA)
        val STRING = ctak("RULES_STRING", DLHC.STRING)

        val COMMENT_KEYS = arrayOf(COMMENT)
        val KEYWORD_KEYS = arrayOf(KEYWORD)
        val NUMBER_KEYS = arrayOf(NUMBER)
        val EMPTY_KEYS = emptyArray<TextAttributesKey>()
        val OPERATOR_KEYS = arrayOf(ASSERTIONS, OPERATORS)
        val VARIABLE_KEYS = arrayOf(VARIABLES)
        val COMMA_KEYS = arrayOf(COMMA)
        val STRING_KEYS = arrayOf(STRING)
    }

    override fun getHighlightingLexer() = RulesLexerAdapter()

    override fun getTokenHighlights(tokenType: IElementType) = when (tokenType) {
        RT.COMMENT -> COMMENT_KEYS
        RT.D_BALANCE_TYPE, RT.D_DATE_FORMAT, RT.D_INCLUDE,
        RT.D_FIELDS, RT.D_SKIP, RT.D_NEWEST_FIRST,
        RT.D_SEPARATOR, RT.D_IF -> KEYWORD_KEYS
        RT.BALANCE_ASSERTION_TYPE, RT.AND -> OPERATOR_KEYS
        RT.COMMA -> COMMA_KEYS
        RT.NUMBER -> NUMBER_KEYS
        RT.STRING -> STRING_KEYS
        RT.FIELD_REF -> VARIABLE_KEYS

        else -> EMPTY_KEYS
    }

}
