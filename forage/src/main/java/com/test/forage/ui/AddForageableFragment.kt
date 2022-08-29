package com.test.forage.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.test.forage.R
import com.test.forage.databinding.FragmentAddForageableBinding
import com.test.forage.model.Forageable
import com.test.forage.ui.viewmodel.ForageableViewModel

/**
 * @author jskim
 * @email jskim@1thefull.com
 * @created 2022/08/29 3:39 오후
 * @desc
 */
class AddForageableFragment: Fragment() {
    private val navigationArgs: AddForageableFragmentArgs by navArgs()
    private var _binding: FragmentAddForageableBinding? = null
    private val binding get() = _binding!!
    private lateinit var forageable: Forageable
    private val viewModel: ForageableViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddForageableBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = navigationArgs.id
        if (id>0){
            binding.deleteBtn.visibility = View.VISIBLE
            binding.deleteBtn.setOnClickListener {
                deleteForageable(forageable)
            }
        }else{
            binding.saveBtn.setOnClickListener {
                addForageabe()
            }
        }
    }

    private fun deleteForageable(forageable: Forageable){
        viewModel.deleteForageable(forageable)
        findNavController().navigate(
            R.id.action_addForageableFragment_to_forageableListFragment
        )
    }

    private fun addForageabe(){
        if (isValidEntry()){
            viewModel.addForageable(
                binding.nameInput.text.toString(),
                binding.locationAddressInput.text.toString(),
                binding.inSeasonCheckbox.isChecked,
                binding.notesInput.text.toString()
            )
            findNavController().navigate(
                R.id.action_addForageableFragment_to_forageableListFragment
            )
        }
    }

    private fun updateForageable(){
        if (isValidEntry()){
            viewModel.updateForageable(
                id = navigationArgs.id,
                name = binding.nameInput.text.toString(),
                address = binding.locationAddressInput.text.toString(),
                inSeason = binding.inSeasonCheckbox.isChecked,
                notes =  binding.notesInput.text.toString()
            )
            findNavController().navigate(
                R.id.action_addForageableFragment_to_forageableListFragment
            )
        }
    }

    private fun bindForageable(forageable: Forageable){
        binding.apply {
            nameInput.setText(forageable.name, TextView.BufferType.SPANNABLE)
            locationAddressInput.setText(forageable.address, TextView.BufferType.SPANNABLE)
            inSeasonCheckbox.isChecked = forageable.inSeason
            notesInput.setText(forageable.notes, TextView.BufferType.SPANNABLE)
            saveBtn.setOnClickListener {
                updateForageable()
            }
        }
    }

    private fun isValidEntry() = viewModel.isValidEntry(
        binding.nameInput.text.toString(),
        binding.locationAddressInput.text.toString()
    )

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}