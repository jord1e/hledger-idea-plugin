package nl.jord1e.idea.pta.journal.lang.psi

import com.intellij.extapi.psi.PsiFileBase
import com.intellij.psi.FileViewProvider
import nl.jord1e.idea.pta.journal.lang.JournalFileType
import nl.jord1e.idea.pta.journal.lang.JournalLanguage

class JournalFile(viewProvider: FileViewProvider) : PsiFileBase(viewProvider, JournalLanguage) {

    override fun getFileType() = JournalFileType

}
