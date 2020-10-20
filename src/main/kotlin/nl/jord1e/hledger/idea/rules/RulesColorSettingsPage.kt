package nl.jord1e.hledger.idea.rules

import com.intellij.icons.AllIcons
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.options.colors.AttributesDescriptor
import com.intellij.openapi.options.colors.ColorDescriptor
import com.intellij.openapi.options.colors.ColorSettingsPage

class RulesColorSettingsPage : ColorSettingsPage {

    // https://jetbrains.org/intellij/sdk/docs/tutorials/custom_language_support/syntax_highlighter_and_color_settings_page.html
    private companion object {
        val DESCRIPTORS = emptyArray<AttributesDescriptor>()
    }

    override fun getAttributeDescriptors() = DESCRIPTORS

    override fun getColorDescriptors(): Array<ColorDescriptor> = ColorDescriptor.EMPTY_ARRAY

    override fun getDisplayName() = "HLedger Rules"

    override fun getIcon() = AllIcons.FileTypes.Properties

    override fun getHighlighter() = RulesSyntaxHighlighter()

    override fun getDemoText() = """
        newest-first
        date-format %m/%d/%y
        fields date,description,csvamount
        skip 12
        
        if,FIELD1,FIELD2,FIELD3
        MATCHER1,VALUE11,VALUE12,VALUE13
        MATCHER2,VALUE21,VALUE22,VALUE23
        
        if
        %xxx [1-9]
        & %zzz ,a.*?b,
         account1  assets:mybank:account1
 
        separator ;
        balance-type ==*
         
        # comment123
        ;comment1234
        if ,,,
         end

        amount1 %amnt EUR
    """.trimIndent()

    override fun getAdditionalHighlightingTagToDescriptorMap(): MutableMap<String, TextAttributesKey>? = null

}
