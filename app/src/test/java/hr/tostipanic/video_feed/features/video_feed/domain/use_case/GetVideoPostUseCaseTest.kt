package hr.tostipanic.video_feed.features.video_feed.domain.use_case

import com.google.common.truth.Truth.assertThat
import hr.tostipanic.video_feed.features.video_feed.domain.model.Video
import hr.tostipanic.video_feed.features.video_feed.domain.repository.FeedRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import kotlin.random.Random

class GetVideoPostUseCaseTest {

    private lateinit var getVideoPostUseCase: GetVideoPostUseCase
    private val fakeRepository = mockk<FeedRepository>()

    @Before
    fun setUp() {
        getVideoPostUseCase = GetVideoPostUseCase(fakeRepository)

        val videos = mutableListOf<Video>()
        val video = Video(
            id = 0,
            createdAt = "2019-08-22T12:22:22+00:00",
            createdBefore = "1 year ago",
            url = "https://test-videos.co.uk/vids/bigbuckbunny/mp4/h264/1080/Big_Buck_Bunny_1080_10s_5MB.mp4",
            poster = "https://content.jwplatform.com/thumbs/JxwtpJDu-720.jpg",
            bookmarked = false,
            description = "DyVsx0zEPzngFcBsL401",
            views = "Dia8cG uDw6590 mHTbM WDia8 cGuDw65 90m HTbM0W"
        )

        ('0'..'9').forEachIndexed { index, c ->
            videos.add(
                video.copy(id = index.toLong())
            )
        }
        videos.shuffle()

        every { runBlocking { fakeRepository.fetchFeed(any(), any()) } } returns videos
        every { runBlocking { fakeRepository.getVideoById(any()) } } returns video
        every { runBlocking { fakeRepository.storeFeed(any()) } } answers { }

    }

    @ExperimentalCoroutinesApi
    @Test
    fun `get video post from repository, returns valid data`() {
        runBlockingTest {
            val id = Random(5).nextInt()
            val feed = getVideoPostUseCase(videoId = id).last()
            verify { runBlocking { fakeRepository.getVideoById(id) } }
            assertThat(feed.data).isNotNull()
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `get video post from repository, returns http exception error message`() {
        runBlockingTest {
            every { runBlocking { fakeRepository.getVideoById(any()) } } throws HttpException(
                Response.error<ResponseBody>(
                    500, "some content"
                        .toResponseBody("plain/text".toMediaTypeOrNull())
                )
            )
            val feed = getVideoPostUseCase(videoId = Random(5).nextInt()).last()
            assertThat(feed.message).contains("HTTP 500")
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `get video post from repository, returns IOException error message`() {
        runBlockingTest {
            every { runBlocking { fakeRepository.getVideoById(any()) } } throws IOException()
            val feed = getVideoPostUseCase(videoId = Random(5).nextInt()).last()
            assertThat(feed.message).contains("Couldn't reach database. Check if there is space left on device.")
        }
    }
}