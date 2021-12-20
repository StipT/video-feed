package hr.tostipanic.video_feed.features.video_feed_list.data.remote

import hr.tostipanic.video_feed.features.video_feed_list.data.remote.response.VideoPost
import retrofit2.http.GET
import retrofit2.http.Query

interface VideoFeedApi {

    @GET("/feed")
    suspend fun getVideoFeed(
        @Query("page") page: String,
        @Query("sport") sport: String
    ): List<VideoPost>
}
