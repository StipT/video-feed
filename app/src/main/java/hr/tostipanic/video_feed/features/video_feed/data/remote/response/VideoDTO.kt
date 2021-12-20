package hr.tostipanic.video_feed.features.video_feed.data.remote.response

import com.google.gson.annotations.SerializedName


data class VideoDTO(
    @SerializedName("handler")
    val handler: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("poster")
    val poster: String?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("length")
    val length: Long?,
)
