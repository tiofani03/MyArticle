package id.tiooooo.myarticle.utils

enum class SortValue(val label: String, val slug: String) {
    DESCENDING("Terbaru", "-published_at"),
    ASCENDING("Terlama", "published_at"),
}

fun createSortValueListSlug(): List<SortValue> {
    return listOf(
        SortValue.DESCENDING,
        SortValue.ASCENDING,
    )
}

fun SortValue.getLabel(): String {
    return when (this) {
        SortValue.ASCENDING -> SortValue.ASCENDING.label
        SortValue.DESCENDING -> SortValue.DESCENDING.label
    }
}

fun SortValue.getSlug(): String {
    return when (this) {
        SortValue.ASCENDING -> SortValue.ASCENDING.slug
        SortValue.DESCENDING -> SortValue.DESCENDING.slug
    }
}