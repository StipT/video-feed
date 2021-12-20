package hr.tostipanic.video_feed.features.video_feed_list.data.repository

import hr.tostipanic.video_feed.features.video_feed_list.data.remote.VideoFeedApi
import hr.tostipanic.video_feed.features.video_feed_list.data.remote.response.VideoPost
import hr.tostipanic.video_feed.features.video_feed_list.domain.repository.FeedRepository
import javax.inject.Inject

class FeedRepositoryImpl @Inject constructor (private val feedApiClient: VideoFeedApi) : FeedRepository {

    override suspend fun getFeed(sport: String, page: String): List<VideoPost> {
        return feedApiClient.getVideoFeed(sport = sport, page = page)
    }
}