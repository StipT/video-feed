package hr.tostipanic.video_feed.features.video_feed.presentation.video_player

import hr.tostipanic.video_feed.features.video_feed.domain.model.Video

data class VideoPlayerState(
    val isLoading: Boolean = false,
    val videoPost: Video? = null,
    val error: String = ""
)
