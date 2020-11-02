package nl.jord1e.idea.pta.journal.ide

import com.intellij.lang.Commenter

class JournalCommenter : Commenter {

//    | Factor | Ledger | hledger | Beancount |
//    |--------|--------|---------|-----------|
//    | ;      | Y      | Y       | Y         |
//    | #      | Y      | Y       |           |
//    | %      | Y      |         |           |
//    | |      | Y      |         |           |
//    | *      | Y      | Y       |           | Code folding?

    private companion object {
        val LINE_COMMENT_PREFIXES = listOf(";", "#", "%", "|", "*")
    }

    override fun getLineCommentPrefix() = ";"

    override fun getBlockCommentPrefix() = "comment"

    override fun getBlockCommentSuffix() = "end comment"

    override fun getCommentedBlockCommentPrefix(): String? = null

    override fun getCommentedBlockCommentSuffix(): String? = null

    override fun getLineCommentPrefixes() = LINE_COMMENT_PREFIXES

}
