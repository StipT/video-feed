package hr.tostipanic.video_feed.features.video_feed.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import hr.tostipanic.video_feed.features.video_feed.domain.model.Video
import kotlinx.coroutines.flow.Flow

@Dao
interface VideoDao {

    @Query("SELECT * FROM video")
    fun getVideos(): Flow<List<Video>>

    @Query("SELECT * FROM video WHERE id = :id")
    suspend fun getVideoById(id: Int): Video?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun storeFeed(note: List<Video>)
}