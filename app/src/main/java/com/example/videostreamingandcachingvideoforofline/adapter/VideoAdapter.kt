package com.example.videostreamingandcachingvideoforofline.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.videostreamingandcachingvideoforofline.R
import com.example.videostreamingandcachingvideoforofline.databinding.ListItemBinding
import com.example.videostreamingandcachingvideoforofline.model.Video
import com.example.videostreamingandcachingvideoforofline.utils.VideoClick

class VideoAdapter(val videoClick: VideoClick) : RecyclerView.Adapter<VideoViewHolder>(){


    /**
     * The videos that our Adapter will show
     */
    var videos: List<Video> = emptyList()
        set(value) {
            field = value
            // For an extra challenge, update this to use the paging library.

            // Notify any registered observers that the data set has changed. This will cause every
            // element in our RecyclerView to be invalidated.
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val withDataBinding: ListItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            VideoViewHolder.LAYOUT,
            parent,
            false)
        return VideoViewHolder(withDataBinding)
    }

    override fun getItemCount(): Int {
        return videos.size
    }

    /**
     * Called by RecyclerView to display the data at the specified position. This method should
     * update the contents of the {@link ViewHolder#itemView} to reflect the item at the given
     * position.
     */
    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.video = videos[position]
            it.videoCallback = videoClick
        }
    }
}


class VideoViewHolder(val viewDataBinding: ListItemBinding): RecyclerView.ViewHolder(viewDataBinding.root){
    companion object{
        @LayoutRes
        val LAYOUT = R.layout.list_item
    }
}