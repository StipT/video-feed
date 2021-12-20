package hr.tostipanic.video_feed.features.video_feed.domain.use_case

import hr.tostipanic.video_feed.features.video_feed.domain.model.Video
import hr.tostipanic.video_feed.features.video_feed.domain.model.wrapper.Resource
import hr.tostipanic.video_feed.features.video_feed.domain.repository.FeedRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetVideoPostUseCase @Inject constructor(
    private val repository: FeedRepository
) {

    operator fun invoke(videoId: Int): Flow<Resource<Video>> = flow {
        try {
            emit(Resource.Loading<Video>())
            val video = repository.getVideoById(videoId)
            emit(Resource.Success<Video>(video as Video))
        } catch (e: HttpException) {
            emit(
                Resource.Error<Video>(
                    e.localizedMessage ?: "An unexpected error occurred"
                )
            )
        } catch (e: IOException) {
            emit(Resource.Error<Video>("Couldn't reach database. Check if there is space left on device."))
        }
    }
}