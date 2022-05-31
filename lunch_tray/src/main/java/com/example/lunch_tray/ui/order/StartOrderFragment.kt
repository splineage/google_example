package com.example.lunch_tray.ui.order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.lunch_tray.R
import com.example.lunch_tray.databinding.FragmentStartOrderBinding
import com.example.lunch_tray.model.OrderViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [StartOrderFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class StartOrderFragment : Fragment() {

    private var _binding: FragmentStartOrderBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStartOrderBinding.inflate(inflater, container, false)
        binding.startOrderBtn.setOnClickListener {
            findNavController().navigate(R.id.action_startOrderFragment_to_entreeMenuFragment)
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}