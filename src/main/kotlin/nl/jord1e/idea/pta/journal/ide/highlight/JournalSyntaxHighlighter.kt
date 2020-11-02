package nl.jord1e.idea.pta.journal.ide.highlight

import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.psi.tree.IElementType
import nl.jord1e.idea.pta.journal.lang.lexer.JournalLexerAdapter
import nl.jord1e.idea.pta.journal.lang.psi.JournalTypes.*
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors as DLHC
import com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey as ctak
import nl.jord1e.idea.pta.journal.lang.psi.JournalTypes as JT

class JournalSyntaxHighlighter : SyntaxHighlighterBase() {

    private companion object {
        val KEYWORD = ctak("JOURNAL_KEYWORD", DLHC.KEYWORD)
        val NUMBER = ctak("JOURNAL_NUMBER", DLHC.NUMBER)
        val PARENTHESES = ctak("JOURNAL_PARENTHESES", DLHC.PARENTHESES)
        val BRACKETS = ctak("JOURNAL_BRACKETS", DLHC.BRACKETS)
        val BRACES = ctak("JOURNAL_BRACES", DLHC.BRACES)
        val OPERATION_SIGN = ctak("JOURNAL_OPERATION_SIGN", DLHC.OPERATION_SIGN)
        val LINE_COMMENT = ctak("JOURNAL_LINE_COMMENT", DLHC.LINE_COMMENT)
        val BLOCK_COMMENT = ctak("JOURNAL_BLOCK_COMMENT", DLHC.BLOCK_COMMENT)

    }

    override fun getHighlightingLexer() = JournalLexerAdapter()

    override fun getTokenHighlights(tokenType: IElementType?) = when (tokenType) {
        LPAREN, RPAREN -> pack(PARENTHESES)
        LBRACKET, RBRACKET -> pack(BRACKETS)
        LBRACE, RBRACE -> pack(BRACES)
        BLOCK_COMMENT_START, BLOCK_COMMENT_CONTENT,
        BLOCK_COMMENT_END -> pack(BLOCK_COMMENT)
        JT.LINE_COMMENT, EOL_COMMENT -> pack(LINE_COMMENT)
        AT, ATAT, ATP, ATATP,
        STAR, EXCLAMATION_MARK,
        EQ, EQEQ, EQSTAR, EQEQSTAR -> pack(OPERATION_SIGN)
        D_ACCOUNT, D_ALIAS, D_APPLY_ACCOUNT,
        D_COMMODITY, D_D, D_P, D_Y, D_INCLUDE,
        D_END_ALIASES, D_END_APPLY_ACCOUNT -> pack(KEYWORD)
        JT.NUMBER, YEAR -> pack(NUMBER)
        else -> TextAttributesKey.EMPTY_ARRAY
    }

}
