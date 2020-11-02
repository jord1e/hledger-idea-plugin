package nl.jord1e.idea.pta.rules.lang.parser

import com.intellij.lang.ASTNode
import com.intellij.lang.ParserDefinition
import com.intellij.openapi.project.Project
import com.intellij.psi.FileViewProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.TokenType
import com.intellij.psi.tree.IFileElementType
import com.intellij.psi.tree.TokenSet
import nl.jord1e.idea.pta.rules.lang.RulesLanguage
import nl.jord1e.idea.pta.rules.lang.lexer.RulesLexerAdapter
import nl.jord1e.idea.pta.rules.lang.psi.RulesFile
import nl.jord1e.idea.pta.rules.lang.psi.RulesTypes
import nl.jord1e.idea.pta.rules.parser.RulesParser

class RulesParserDefinition : ParserDefinition {

    companion object {
        val STRING_LITERALS = TokenSet.create(TokenType.WHITE_SPACE, RulesTypes.STRING)
        val COMMENTS = TokenSet.create(RulesTypes.COMMENT)
        val FILE = IFileElementType(RulesLanguage)
    }

    override fun createParser(project: Project?) = RulesParser()

    override fun createFile(viewProvider: FileViewProvider) = RulesFile(viewProvider)

    override fun getStringLiteralElements() = STRING_LITERALS

    override fun getFileNodeType() = FILE

    override fun createLexer(project: Project) = RulesLexerAdapter()

    override fun createElement(node: ASTNode): PsiElement = RulesTypes.Factory.createElement(node)

    override fun getCommentTokens() = COMMENTS

}
