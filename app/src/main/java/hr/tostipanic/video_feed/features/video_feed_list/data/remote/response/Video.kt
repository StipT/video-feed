package hr.tostipanic.video_feed.features.video_feed_list.data.remote.response

import com.google.gson.annotations.SerializedName


data class Video(
    @SerializedName("handler")
    val handler: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("poster")
    val poster: String?,
    @SerializedName( "type")
    val type: String?,
    @SerializedName("length")
    val length: Long?,
)
