package com.example.videostreamingandcachingvideoforofline.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NetworkVideo(
    val title: String,
    val description: String,
    val url: String,
    val updated: String,
    val thumbnail: String,
    val closedCaptions: String?
)
