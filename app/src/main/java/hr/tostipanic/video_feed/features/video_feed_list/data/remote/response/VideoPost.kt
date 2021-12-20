package hr.tostipanic.video_feed.features.video_feed_list.data.remote.response

import com.google.gson.annotations.SerializedName


data class VideoPost(
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
    val video: Video?,
    @SerializedName("views")
    val views: String?
)
