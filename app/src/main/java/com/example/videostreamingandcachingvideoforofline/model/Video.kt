package com.example.videostreamingandcachingvideoforofline.model

import com.example.videostreamingandcachingvideoforofline.utils.smartTruncate

data class Video(
    val title: String,
    val description: String,
    val url: String,
    val updated: String,
    val thumbnail: String
){
    val shortDescription: String
        get() = description.smartTruncate(200)
}
