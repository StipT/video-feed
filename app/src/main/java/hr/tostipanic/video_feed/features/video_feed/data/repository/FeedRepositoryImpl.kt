package hr.tostipanic.video_feed.features.video_feed.data.repository

import hr.tostipanic.video_feed.features.video_feed.data.local.VideoDatabase
import hr.tostipanic.video_feed.features.video_feed.data.remote.VideoFeedApi
import hr.tostipanic.video_feed.features.video_feed.data.remote.response.toVideo
import hr.tostipanic.video_feed.features.video_feed.domain.model.Video
import hr.tostipanic.video_feed.features.video_feed.domain.repository.FeedRepository
import javax.inject.Inject

class FeedRepositoryImpl @Inject constructor(
    private val feedApiClient: VideoFeedApi,
    private val videoDatabase: VideoDatabase
) : FeedRepository {

    override suspend fun fetchFeed(sport: String, page: String): List<Video> {
        return feedApiClient.getVideoFeed(sport = sport, page = page).map { it.toVideo() }
    }

    override suspend fun storeFeed(feed: List<Video>) {
        return videoDatabase.videoDao.storeFeed(feed)
    }

    override suspend fun getVideoById(id: Int): Video? {
        return videoDatabase.videoDao.getVideoById(id)
    }
}
