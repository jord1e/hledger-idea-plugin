package nl.jord1e.hledger.idea.rules.psi

import com.intellij.extapi.psi.PsiFileBase
import com.intellij.psi.FileViewProvider
import nl.jord1e.hledger.idea.rules.RulesFileType
import nl.jord1e.hledger.idea.rules.RulesLanguage

class RulesFile(viewProvider: FileViewProvider) : PsiFileBase(viewProvider, RulesLanguage) {

    override fun getFileType() = RulesFileType

    override fun toString() = "Hledger Rules File"

}
