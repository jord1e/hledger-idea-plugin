package nl.jord1e.hledger.idea.rules

import com.intellij.lang.ASTNode
import com.intellij.lang.ParserDefinition
import com.intellij.openapi.project.Project
import com.intellij.psi.FileViewProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.TokenType
import com.intellij.psi.tree.IFileElementType
import com.intellij.psi.tree.TokenSet
import nl.jord1e.hledger.idea.rules.parser.RulesParser
import nl.jord1e.hledger.idea.rules.psi.RulesFile
import nl.jord1e.hledger.idea.rules.psi.RulesTypes

class RulesParserDefinition : ParserDefinition {

    companion object {
        val WHITE_SPACES = TokenSet.create(TokenType.WHITE_SPACE)
        val COMMENTS = TokenSet.create(RulesTypes.COMMENT)
        val FILE = IFileElementType(RulesLanguage)
    }

    override fun createParser(project: Project?) = RulesParser()

    override fun createFile(viewProvider: FileViewProvider) = RulesFile(viewProvider)

    override fun getStringLiteralElements() = WHITE_SPACES

    override fun getFileNodeType() = FILE

    override fun createLexer(project: Project) = RulesLexerAdapter()

    override fun createElement(node: ASTNode): PsiElement = RulesTypes.Factory.createElement(node)

    override fun getCommentTokens() = COMMENTS

}
