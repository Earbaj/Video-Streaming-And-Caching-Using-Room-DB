package com.example.videostreamingandcachingvideoforofline.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.videostreamingandcachingvideoforofline.R
import com.example.videostreamingandcachingvideoforofline.adapter.VideoAdapter
import com.example.videostreamingandcachingvideoforofline.databinding.FragmentDetailsBinding
import com.example.videostreamingandcachingvideoforofline.model.Video
import com.example.videostreamingandcachingvideoforofline.viewmodel.DetailsViewModel
import com.example.videostreamingandcachingvideoforofline.viewmodel.Factory

class DetailsFragment : Fragment() {

    private val viewModel: DetailsViewModel by lazy {
        val activity = requireNotNull(this.activity){
            "You can only access the viewModel after onViewCreated()"
        }
        ViewModelProvider(this, Factory(activity.application)).get(DetailsViewModel::class.java)
    }

    /**
     * RecyclerView Adapter for converting a list of Video to cards.
     */
    private var videoAdapter: VideoAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.playlist.observe(viewLifecycleOwner, Observer<List<Video>>{
            it.apply {
                videoAdapter?.videos = it
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val bindings = FragmentDetailsBinding.inflate(layoutInflater)
        bindings.lifecycleOwner = this
        bindings.viewModel = viewModel
        videoAdapter = VideoAdapter()
        bindings.root.findViewById<RecyclerView>(R.id.recycler_view).apply {
            layoutManager = LinearLayoutManager(context)
            adapter = videoAdapter
        }
        return bindings.root
    }

}