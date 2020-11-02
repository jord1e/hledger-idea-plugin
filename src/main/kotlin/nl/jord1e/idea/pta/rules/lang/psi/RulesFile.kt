package nl.jord1e.idea.pta.rules.lang.psi

import com.intellij.extapi.psi.PsiFileBase
import com.intellij.psi.FileViewProvider
import nl.jord1e.idea.pta.rules.lang.RulesFileType
import nl.jord1e.idea.pta.rules.lang.RulesLanguage

class RulesFile(viewProvider: FileViewProvider) : PsiFileBase(viewProvider, RulesLanguage) {

    override fun getFileType() = RulesFileType

    override fun toString() = "RulesFile:$name";


}
