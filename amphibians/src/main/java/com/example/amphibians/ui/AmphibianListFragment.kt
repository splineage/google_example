package com.example.amphibians.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.amphibians.R
import com.example.amphibians.databinding.FragmentAmphibianListBinding
import com.example.amphibians.network.Amphibian

class AmphibianListFragment : Fragment(){

    private val viewModel: AmphibianViewModel by activityViewModels()

    private var _binding: FragmentAmphibianListBinding? = null
    private val binding get() = _binding!!

    val amphibianListener: AmphibianListener = AmphibianListener {
        viewModel.onAmphibianClicked(it)
        findNavController()
            .navigate(R.id.action_amphibianListFragment_to_amphibianDetailFragment)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAmphibianListBinding.inflate(inflater)
        binding.recyclerView.adapter = AmphibianListAdapter(amphibianListener)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            lifecycleOwner = this@AmphibianListFragment
            viewModel = this@AmphibianListFragment.viewModel
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}