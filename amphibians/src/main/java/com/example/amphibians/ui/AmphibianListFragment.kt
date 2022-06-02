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

class AmphibianListFragment : Fragment() {

    private val viewModel: AmphibianViewModel by activityViewModels()

    private var _binding: FragmentAmphibianListBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAmphibianListBinding.inflate(inflater)
        binding.apply {
            lifecycleOwner = this@AmphibianListFragment
            viewModel = this@AmphibianListFragment.viewModel
            recyclerView.adapter = AmphibianListAdapter(AmphibianListener { amphibian ->
                this@AmphibianListFragment.viewModel.onAmphibianClicked(amphibian)
                findNavController()
                    .navigate(R.id.action_amphibianListFragment_to_amphibianDetailFragment)
            })
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}