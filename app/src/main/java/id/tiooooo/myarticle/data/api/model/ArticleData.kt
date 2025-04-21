package id.tiooooo.myarticle.data.api.model

data class ArticleData(
    val id: Int,
    val title: String,
    val authors: List<String>,
    val imageUrl: String,
    val summary: String,
    val publishedAt: String,
    val articleUrl: String,
    val source: String,
    val launches: List<String>,
    val events: List<String>,
)

