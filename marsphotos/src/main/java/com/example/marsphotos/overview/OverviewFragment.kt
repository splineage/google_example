package com.example.marsphotos.overview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.marsphotos.databinding.FragmentOverviewBinding
import com.example.marsphotos.databinding.GridViewItemBinding

class OverviewFragment: Fragment() {
    private val overviewViewModel: OverviewViewModel by viewModels()

    private var _binding: FragmentOverviewBinding? = null
    private val binding: FragmentOverviewBinding get() = _binding!!
//    lateinit var binding: GridViewItemBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentOverviewBinding.inflate(inflater)
//        binding = GridViewItemBinding.inflate(inflater)
        binding.photosGrid.adapter = PhotoGridAdapter()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = overviewViewModel
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}