package nl.jord1e.idea.pta.rules.lang

import com.intellij.openapi.fileTypes.LanguageFileType

object RulesFileType : LanguageFileType(RulesLanguage) {

    override fun getName() = "Hledger Rules"

    override fun getDefaultExtension() = "rules"

    override fun getIcon() = RulesIcons.RULES_FILE

    override fun getDescription() = "Hledger Rules File"

}
