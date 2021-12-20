package hr.tostipanic.video_feed.features.video_feed_list.presentation

import hr.tostipanic.video_feed.features.video_feed_list.data.remote.response.VideoPost

data class FeedState(
    val isLoading: Boolean = false,
    val feed: List<VideoPost> = emptyList(),
    val error: String = ""
)
