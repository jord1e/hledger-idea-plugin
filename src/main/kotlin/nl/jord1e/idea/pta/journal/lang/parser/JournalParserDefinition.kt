package nl.jord1e.idea.pta.journal.lang.parser

import com.intellij.lang.ASTNode
import com.intellij.lang.ParserDefinition
import com.intellij.openapi.project.Project
import com.intellij.psi.FileViewProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.TokenType
import com.intellij.psi.tree.IFileElementType
import com.intellij.psi.tree.TokenSet
import nl.jord1e.idea.pta.journal.lang.JournalLanguage
import nl.jord1e.idea.pta.journal.lang.lexer.JournalLexerAdapter
import nl.jord1e.idea.pta.journal.lang.psi.JournalFile
import nl.jord1e.idea.pta.journal.lang.psi.JournalTypes
import nl.jord1e.idea.pta.journal.parser.JournalParser

class JournalParserDefinition : ParserDefinition {

    private companion object {
        val STRING_LITERALS = TokenSet.create(TokenType.WHITE_SPACE)
        val COMMENTS = TokenSet.create(JournalTypes.BLOCK_COMMENT, JournalTypes.LINE_COMMENT, JournalTypes.EOL_COMMENT)
        val FILE = IFileElementType(JournalLanguage)
    }

    override fun createLexer(project: Project?) = JournalLexerAdapter()

    override fun createParser(project: Project?) = JournalParser()

    override fun getFileNodeType() = FILE

    override fun getCommentTokens() = COMMENTS

    override fun getStringLiteralElements() = STRING_LITERALS

    override fun createElement(node: ASTNode?): PsiElement = JournalTypes.Factory.createElement(node)

    override fun createFile(viewProvider: FileViewProvider) = JournalFile(viewProvider)

}
