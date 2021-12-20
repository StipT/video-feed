package hr.tostipanic.video_feed.features.video_feed_list.domain.repository

import hr.tostipanic.video_feed.features.video_feed_list.data.remote.response.VideoPost

interface FeedRepository {

    suspend fun getFeed(sport: String, page: String): List<VideoPost>
}
