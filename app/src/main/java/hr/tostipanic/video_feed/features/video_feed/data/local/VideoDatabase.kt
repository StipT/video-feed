package hr.tostipanic.video_feed.features.video_feed.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import hr.tostipanic.video_feed.features.video_feed.domain.model.Video

@Database(
    entities = [Video::class], exportSchema = false,
    version = 1
)
abstract class VideoDatabase : RoomDatabase() {

    abstract val videoDao: VideoDao

    companion object {
        const val DATABASE_NAME = "video_database"
    }
}