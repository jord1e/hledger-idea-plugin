package nl.jord1e.hledger.idea.rules

import com.intellij.icons.AllIcons
import com.intellij.openapi.fileTypes.LanguageFileType

object RulesFileType : LanguageFileType(RulesLanguage) {

    override fun getName() = "Hledger Rules"

    override fun getDefaultExtension() = "rules"

    override fun getIcon() = AllIcons.FileTypes.Custom

    override fun getDescription() = "Hledger Rules File"

}
