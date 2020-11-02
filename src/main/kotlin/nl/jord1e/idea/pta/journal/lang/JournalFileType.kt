package nl.jord1e.idea.pta.journal.lang

import com.intellij.icons.AllIcons
import com.intellij.openapi.fileTypes.LanguageFileType

object JournalFileType : LanguageFileType(JournalLanguage) {

    override fun getName() = "Journal"

    override fun getDescription() = "Ledger Journal File"

    override fun getDefaultExtension() = "journal"

    override fun getIcon() = AllIcons.FileTypes.Config

}
