package hr.tostipanic.video_feed.di

import android.app.Application
import androidx.room.Room

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hr.tostipanic.video_feed.BuildConfig
import hr.tostipanic.video_feed.features.video_feed.data.local.VideoDatabase
import hr.tostipanic.video_feed.features.video_feed.data.remote.VideoFeedApi
import hr.tostipanic.video_feed.features.video_feed.data.repository.FeedRepositoryImpl
import hr.tostipanic.video_feed.features.video_feed.domain.repository.FeedRepository
import hr.tostipanic.video_feed.features.video_feed.domain.use_case.GetVideoFeedUseCase
import hr.tostipanic.video_feed.features.video_feed.domain.use_case.GetVideoPostUseCase
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

private const val TIME_OUT = 30L

@Module
@InstallIn(SingletonComponent::class)
object TestAppModule {

    @Provides
    @Singleton
    fun provideVideoDatabase(app: Application): VideoDatabase {
        return Room.inMemoryDatabaseBuilder(
            app,
            VideoDatabase::class.java,
        ).build()
    }

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
            .baseUrl(BuildConfig.URL_API)
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
    fun provideVideoRepository(api: VideoFeedApi, db: VideoDatabase): FeedRepository {
        return FeedRepositoryImpl(api, db)
    }

    @Provides
    @Singleton
    fun provideGetVideoFeedUseCase(repository: FeedRepository): GetVideoFeedUseCase {
        return GetVideoFeedUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetVideoPostdUseCase(repository: FeedRepository): GetVideoPostUseCase {
        return GetVideoPostUseCase(repository)
    }

    
}