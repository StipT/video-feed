package hr.tostipanic.video_feed.features.video_feed.domain.repository

import hr.tostipanic.video_feed.features.video_feed.domain.model.Video

interface FeedRepository {

    suspend fun fetchFeed(sport: String, page: String): List<Video>

    suspend fun getVideoById(id: Int): Video?

    suspend fun storeFeed(feed: List<Video>)
}
