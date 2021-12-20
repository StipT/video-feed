package hr.tostipanic.video_feed.features.video_feed.data.remote

import hr.tostipanic.video_feed.features.video_feed.data.remote.response.VideoPostDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface VideoFeedApi {

    @GET("/feed")
    suspend fun getVideoFeed(
        @Query("page") page: String,
        @Query("sport") sport: String
    ): List<VideoPostDTO>
}
