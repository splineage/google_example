package com.test.forage.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.test.forage.R
import com.test.forage.databinding.FragmentForageableListBinding
import com.test.forage.model.Forageable
import com.test.forage.ui.adapter.ForageableListAdapter
import com.test.forage.ui.viewmodel.ForageableViewModel

/**
 * @author jskim
 * @email jskim@1thefull.com
 * @created 2022/08/29 3:30 오후
 * @desc
 */
class ForageableListFragment: Fragment() {
    private val viewModel: ForageableViewModel by activityViewModels()
    private var _binding: FragmentForageableListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentForageableListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = ForageableListAdapter{forageable: Forageable ->
            val action = ForageableListFragmentDirections
                .actionForageableListFragmentToForageableDetailFragment(forageable.id)
            findNavController().navigate(action)
        }
        binding.apply {
            recyclerView.adapter = adapter
            addForageableFab.setOnClickListener {
                findNavController().navigate(R.id.action_forageableListFragment_to_addForageableFragment)
            }
        }
    }
}