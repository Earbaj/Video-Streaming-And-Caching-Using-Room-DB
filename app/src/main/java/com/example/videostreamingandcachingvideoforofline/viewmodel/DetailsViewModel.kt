package com.example.videostreamingandcachingvideoforofline.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.videostreamingandcachingvideoforofline.model.Video
import com.example.videostreamingandcachingvideoforofline.model.asDomainModel
import com.example.videostreamingandcachingvideoforofline.network.Network
import kotlinx.coroutines.launch
import java.io.IOException

class DetailsViewModel(application: Application): AndroidViewModel(application) {
    private val _playlist = MutableLiveData<List<Video>>()
    val playlist: LiveData<List<Video>> = _playlist

    /**
     * init{} is called immediately when this ViewModel is created.
     */
    init {
        refreshDataFromNetwork()
    }

    /**
     * Refresh data from network and pass it via LiveData. Use a coroutine launch to get to
     * background thread.
     */
    fun refreshDataFromNetwork(){
        viewModelScope.launch {
            try {
                val playlist = Network.videobytes.getPlaylist().await()
                _playlist.postValue(playlist.asDomainModel())
            } catch (networkError: IOException) {
                // Show an infinite loading spinner if the request fails
                // challenge exercise: show an error to the user if the network request fails
            }
        }
    }

}