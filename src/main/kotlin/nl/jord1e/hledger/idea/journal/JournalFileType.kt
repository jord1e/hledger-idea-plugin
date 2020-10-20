package nl.jord1e.hledger.idea.journal

import com.intellij.icons.AllIcons
import com.intellij.openapi.fileTypes.LanguageFileType

object JournalFileType : LanguageFileType(JournalLanguage) {

    override fun getName() = "Ledger Journal"

    override fun getDefaultExtension() = "journal"

    override fun getIcon() = AllIcons.FileTypes.JsonSchema

    override fun getDescription() = "Ledger Journal"

}
