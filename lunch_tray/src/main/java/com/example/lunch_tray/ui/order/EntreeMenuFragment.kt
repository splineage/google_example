package com.example.lunch_tray.ui.order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.lunch_tray.R
import com.example.lunch_tray.databinding.FragmentEntreeMenuBinding
import com.example.lunch_tray.model.OrderViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [EntreeMenuFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EntreeMenuFragment : Fragment() {

    private var _binding: FragmentEntreeMenuBinding? = null
    private val binding get() = _binding!!

    private val sharedViewModel: OrderViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEntreeMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = sharedViewModel
            entreeFragment = this@EntreeMenuFragment
        }
    }

    fun goToNextScreen(){
        findNavController().navigate(R.id.action_entreeMenuFragment_to_sideMenuFragment)
    }

    fun cancelOrder(){
        sharedViewModel.resetOrder()
        findNavController().navigate(R.id.action_entreeMenuFragment_to_startOrderFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}