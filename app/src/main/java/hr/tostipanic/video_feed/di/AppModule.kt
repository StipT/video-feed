package hr.tostipanic.video_feed.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hr.tostipanic.video_feed.BuildConfig.URL_API
import hr.tostipanic.video_feed.features.video_feed_list.data.remote.VideoFeedApi
import hr.tostipanic.video_feed.features.video_feed_list.data.repository.FeedRepositoryImpl
import hr.tostipanic.video_feed.features.video_feed_list.domain.repository.FeedRepository
import hr.tostipanic.video_feed.features.video_feed_list.domain.use_case.GetVideoFeedUseCase
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

private const val TIME_OUT = 30L

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun createOkHttpClient(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
        return OkHttpClient.Builder()
            .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
            .readTimeout(TIME_OUT, TimeUnit.SECONDS)
            .writeTimeout(TIME_OUT, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor).build()
    }

    @Provides
    @Singleton
    fun createRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(URL_API)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }


    @Provides
    @Singleton
    fun createService(retrofit: Retrofit): VideoFeedApi {
        return retrofit.create(VideoFeedApi::class.java)
    }

    @Provides
    @Singleton
    fun createFeedRepository(apiService: VideoFeedApi): FeedRepository {
        return FeedRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun createGetFeedUseCase(postsRepository: FeedRepository): GetVideoFeedUseCase {
        return GetVideoFeedUseCase(postsRepository)
    }
}
