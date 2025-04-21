package id.tiooooo.myarticle.data.implementation.remote.response

import com.google.gson.annotations.SerializedName
import id.tiooooo.myarticle.data.api.model.ArticleData
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

data class ArticleResponse(
    @SerializedName("id") val id: Int?,
    @SerializedName("title") val title: String?,
    @SerializedName("authors") val authors: List<AuthorResponse>?,
    @SerializedName("url") val url: String?,
    @SerializedName("image_url") val imageUrl: String?,
    @SerializedName("news_site") val newsSite: String?,
    @SerializedName("summary") val summary: String?,
    @SerializedName("published_at") val publishedAt: String?,
    @SerializedName("updated_at") val updatedAt: String?,
    @SerializedName("featured") val featured: Boolean?,
    @SerializedName("launches") val launches: List<LaunchResponse>?,
    @SerializedName("events") val events: List<EventResponse>?,
)

data class AuthorResponse(
    @SerializedName("name") val name: String?,
)

data class LaunchResponse(
    @SerializedName("launch_id") val launchId: String?,
    @SerializedName("provider") val provider: String?,
)

data class EventResponse(
    @SerializedName("event_id") val eventId: Int?, @SerializedName("provider") val provider: String?
)


fun ArticleResponse?.toDomain(): ArticleData {
    return ArticleData(
        id = this?.id ?: 0,
        title = this?.title ?: "",
        authors = this?.authors?.map { it.name ?: "" } ?: emptyList(),
        imageUrl = this?.imageUrl ?: "",
        summary = this?.summary?.substringBefore(".").orEmpty() + ".",
        publishedAt = this?.publishedAt?.toIndonesianDate().orEmpty(),
        articleUrl = this?.url ?: "",
        source = this?.newsSite ?: "",
        launches = this?.launches?.map { it.launchId ?: "" } ?: emptyList(),
        events = this?.events?.map { it.eventId.toString() } ?: emptyList()
    )
}

fun String.toIndonesianDate(): String {
    return try {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
        inputFormat.timeZone = TimeZone.getTimeZone("UTC")

        val date = inputFormat.parse(this)

        val outputFormat = SimpleDateFormat("d MMMM yyyy, HH:mm", Locale.getDefault())
        date?.let { outputFormat.format(it) } ?: this
    } catch (e: Exception) {
        return this
    }
}


