package nl.jord1e.hledger.idea.journal.psi

import com.intellij.extapi.psi.PsiFileBase
import com.intellij.psi.FileViewProvider
import nl.jord1e.hledger.idea.journal.JournalFileType
import nl.jord1e.hledger.idea.journal.JournalLanguage

class JournalFile(viewProvider: FileViewProvider) : PsiFileBase(viewProvider, JournalLanguage) {

    override fun getFileType() = JournalFileType

    override fun toString() = "Ledger Journal"

}
