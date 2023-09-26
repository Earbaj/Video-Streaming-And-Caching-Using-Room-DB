package com.example.videostreamingandcachingvideoforofline.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DatabaseVideo::class], version = 1, exportSchema = false)
abstract class VideoDatabase : RoomDatabase(){
    abstract fun VideoDao(): VideoDao
    companion object{
        @Volatile
        private var INSTANCE: VideoDatabase? = null
        fun getDatabase(context: Context): VideoDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    VideoDatabase::class.java,
                    "video_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}