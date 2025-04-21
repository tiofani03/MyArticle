package id.tiooooo.myarticle.utils

enum class DATATYPE {
    ARTICLE,
    BLOG,
    REPORT
}

fun DATATYPE.toStringType(): String {
    return when (this) {
        DATATYPE.ARTICLE -> "Article"
        DATATYPE.BLOG -> "Blog"
        DATATYPE.REPORT -> "Report"
    }
}