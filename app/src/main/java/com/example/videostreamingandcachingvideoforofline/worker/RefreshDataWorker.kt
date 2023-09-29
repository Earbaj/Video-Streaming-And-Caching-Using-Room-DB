package com.example.videostreamingandcachingvideoforofline.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.videostreamingandcachingvideoforofline.database.VideoDatabase.Companion.getDatabase
import com.example.videostreamingandcachingvideoforofline.database.VideoRepository
import retrofit2.HttpException

class RefreshDataWorker(appContext: Context, params: WorkerParameters): CoroutineWorker(appContext, params) {


    companion object {
        const val WORK_NAME = "RefreshDataWorker"
    }

    override suspend fun doWork(): Result {
        val database = getDatabase(applicationContext)
        val repository = VideoRepository(database)
        return try {
            repository.refreshVideos()
            Result.success()
        } catch (e: HttpException) {
            Result.retry()
        }
    }
}