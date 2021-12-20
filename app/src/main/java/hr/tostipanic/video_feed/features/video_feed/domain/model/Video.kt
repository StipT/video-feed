package hr.tostipanic.video_feed.features.video_feed.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "video")
data class Video(
    @PrimaryKey
    val id: Long,
    val createdAt: String,
    val createdBefore: String?,
    val description: String?,
    val bookmarked: Boolean?,
    val url: String?,
    val poster: String?,
    val views: String?,
)
