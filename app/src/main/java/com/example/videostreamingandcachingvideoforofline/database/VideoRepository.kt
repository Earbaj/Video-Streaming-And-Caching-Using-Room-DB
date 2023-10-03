package com.example.videostreamingandcachingvideoforofline.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.videostreamingandcachingvideoforofline.model.Video
import com.example.videostreamingandcachingvideoforofline.model.asDatabaseModel
import com.example.videostreamingandcachingvideoforofline.network.Network
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

class VideoRepository(private val database: VideoDatabase) {
    /**
     * A playlist of videos that can be shown on the screen.
     */
//    val videos: LiveData<List<Video>> =
//        Transformations.map(database.VideoDao().getVideos()) {
//            it.asDomainModel()
//        }
    val videos: LiveData<List<Video>> = database.VideoDao().getVideos().map {
        it.asDomainModel()
    }

    /**
     * Refresh the videos stored in the offline cache.
     *
     * This function uses the IO dispatcher to ensure the database insert database operation
     * happens on the IO dispatcher. By switching to the IO dispatcher using `withContext` this
     * function is now safe to call from any thread including the Main thread.
     *
     * To actually load the videos for use, observe [videos]
     */
    suspend fun refreshVideos() {
        withContext(Dispatchers.IO) {
            try{
                val playlist = Network.videobytes.getPlaylist().await()
                database.VideoDao().insertAll(*playlist.asDatabaseModel())
            }catch (e: Exception){
                Timber.e("Updated playlist not available")
            }
        }
    }


}