package com.test.forage.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.test.forage.R
import com.test.forage.databinding.FragmentForageableDetailBinding
import com.test.forage.model.Forageable
import com.test.forage.ui.viewmodel.ForageableViewModel

/**
 * @author jskim
 * @email jskim@1thefull.com
 * @created 2022/08/29 3:31 오후
 * @desc
 */
class ForageableDetailFragment: Fragment() {
    private val navigationArgs: ForageableDetailFragmentArgs by navArgs()
    private val viewModel: ForageableViewModel by activityViewModels()
    private lateinit var forageabe: Forageable
    private var _binding: FragmentForageableDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentForageableDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = navigationArgs.id

    }

    private fun bindForageable(){
        binding.apply {
            name.text = forageabe.name
            locaton.text = forageabe.address
            notes.text = forageabe.notes
            if (forageabe.inSeason){
                season.text = getString(R.string.in_season)
            }else{
                season.text = getString(R.string.out_of_season)
            }
            editForageableFab.setOnClickListener{
                val action = ForageableDetailFragmentDirections.actionForageableDetailFragmentToAddForageableFragment(forageabe.id)
                findNavController().navigate(action)
            }
            locaton.setOnClickListener { launchMap() }
        }
    }

    private fun launchMap(){
        val address = forageabe.address.let {
            it.replace(", ",",")
            it.replace("."," ")
            it.replace(" ","+")
        }
        val gmmIntentUri = Uri.parse("geo:0,0?q=$address")
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps")
        startActivity(mapIntent)
    }
}