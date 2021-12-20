package hr.tostipanic.video_feed.features.video_feed.presentation.video_feed_list

import hr.tostipanic.video_feed.features.video_feed.domain.model.Video

data class FeedState(
    val isLoading: Boolean = false,
    val feed: List<Video> = emptyList(),
    val error: String = ""
)
