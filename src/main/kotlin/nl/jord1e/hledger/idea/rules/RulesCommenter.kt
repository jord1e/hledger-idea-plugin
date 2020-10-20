package nl.jord1e.hledger.idea.rules

import com.intellij.lang.Commenter

class RulesCommenter : Commenter {

    override fun getLineCommentPrefix() = "#"

    override fun getLineCommentPrefixes() = listOf("#", ";")

    override fun getBlockCommentPrefix(): String? = null

    override fun getBlockCommentSuffix(): String? = null

    override fun getCommentedBlockCommentPrefix(): String? = null

    override fun getCommentedBlockCommentSuffix(): String? = null

}
