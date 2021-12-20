package hr.tostipanic.video_feed.features.video_feed.domain.use_case

import hr.tostipanic.video_feed.features.video_feed.domain.model.Video
import hr.tostipanic.video_feed.features.video_feed.domain.model.wrapper.Resource
import hr.tostipanic.video_feed.features.video_feed.domain.repository.FeedRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetVideoFeedUseCase @Inject constructor(
    private val repository: FeedRepository
) {

    operator fun invoke(sport: String, page: String): Flow<Resource<List<Video>>> = flow {
        try {
            emit(Resource.Loading<List<Video>>())
            val feed = repository.fetchFeed(sport = sport, page = page)
            repository.storeFeed(feed)
            emit(Resource.Success<List<Video>>(feed))
        } catch (e: HttpException) {
            emit(
                Resource.Error<List<Video>>(
                    e.localizedMessage ?: "An unexpected error occurred"
                )
            )
        } catch (e: IOException) {
            emit(Resource.Error<List<Video>>("Couldn't reach server. Check your internet connection."))
        }
    }
}
