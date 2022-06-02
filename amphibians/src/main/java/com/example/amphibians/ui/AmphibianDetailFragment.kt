package com.example.amphibians.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.amphibians.R
import com.example.amphibians.databinding.FragmentAmphibianDetailBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AmphibianDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AmphibianDetailFragment : Fragment() {

    private var _binding: FragmentAmphibianDetailBinding? = null

    private val binding get() = _binding!!

    private val viewModel: AmphibianViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAmphibianDetailBinding.inflate(inflater)
        binding.apply {
            lifecycleOwner = this@AmphibianDetailFragment
            viewModel = this@AmphibianDetailFragment.viewModel
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}