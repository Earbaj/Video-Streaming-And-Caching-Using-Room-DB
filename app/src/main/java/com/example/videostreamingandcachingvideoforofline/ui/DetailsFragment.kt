package com.example.videostreamingandcachingvideoforofline.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.videostreamingandcachingvideoforofline.R
import com.example.videostreamingandcachingvideoforofline.databinding.FragmentDetailsBinding
import com.example.videostreamingandcachingvideoforofline.viewmodel.DetailsViewModel
import com.example.videostreamingandcachingvideoforofline.viewmodel.Factory

class DetailsFragment : Fragment() {

    private val viewModel: DetailsViewModel by lazy {
        val activity = requireNotNull(this.activity){
            "You can only access the viewModel after onViewCreated()"
        }
        ViewModelProvider(this, Factory(activity.application)).get(DetailsViewModel::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val bindings = FragmentDetailsBinding.inflate(layoutInflater)
        bindings.lifecycleOwner = this
        bindings.viewModel = viewModel
        return bindings.root
    }

}