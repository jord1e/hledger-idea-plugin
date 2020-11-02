package nl.jord1e.idea.pta.journal.ide

import com.intellij.lang.BracePair
import com.intellij.lang.PairedBraceMatcher
import com.intellij.psi.PsiFile
import com.intellij.psi.tree.IElementType
import nl.jord1e.idea.pta.journal.lang.psi.JournalTypes

class JournalBraceMatcher : PairedBraceMatcher {

    private companion object {
        val PAIRS = arrayOf(
            BracePair(JournalTypes.LBRACE, JournalTypes.RBRACE, true),
            BracePair(JournalTypes.LBRACKET, JournalTypes.RBRACKET, true),
            BracePair(JournalTypes.LPAREN, JournalTypes.RPAREN, true)
        )
    }

    override fun getPairs() = PAIRS

    // TODO (2020-11-01/J): Take context into account?
    override fun isPairedBracesAllowedBeforeType(lbraceType: IElementType, contextType: IElementType?) = true

    override fun getCodeConstructStart(file: PsiFile?, openingBraceOffset: Int) = openingBraceOffset

}
