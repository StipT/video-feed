package hr.tostipanic.video_feed.features.video_feed_list.domain.use_case

import hr.tostipanic.video_feed.features.video_feed_list.data.remote.response.VideoPost
import hr.tostipanic.video_feed.features.video_feed_list.domain.repository.FeedRepository
import hr.tostipanic.video_feed.features.video_feed_list.domain.model.wrapper.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetVideoFeedUseCase @Inject constructor(
    private val repository: FeedRepository
) {

    operator fun invoke(sport: String, page: String): Flow<Resource<List<VideoPost>>> = flow {
        try {
            emit(Resource.Loading<List<VideoPost>>())
            val feed = repository.getFeed(sport = sport, page = page)
            emit(Resource.Success<List<VideoPost>>(feed))
        } catch (e: HttpException) {
            emit(
                Resource.Error<List<VideoPost>>(
                    e.localizedMessage ?: "An unexpected error occured"
                )
            )
        } catch (e: IOException) {
            emit(Resource.Error<List<VideoPost>>("Couldn't reach server. Check your internet connection."))
        }
    }
}
