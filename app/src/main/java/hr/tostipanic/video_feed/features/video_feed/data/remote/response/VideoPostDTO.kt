package hr.tostipanic.video_feed.features.video_feed.data.remote.response

import com.google.gson.annotations.SerializedName
import hr.tostipanic.video_feed.features.video_feed.domain.model.Video

data class VideoPostDTO(
    @SerializedName("id")
    val id: Long,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("createdBefore")
    val createdBefore: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("bookmarked")
    val bookmarked: Boolean?,
    @SerializedName("video")
    val video: VideoDTO?,
    @SerializedName("views")
    val views: String?
)

fun VideoPostDTO.toVideo(): Video {
    return Video(
        id = id,
        createdAt = createdAt,
        createdBefore = createdBefore,
        description = description,
        bookmarked = bookmarked,
        views = views,
        url = video?.url,
        poster = video?.poster
    )
}
