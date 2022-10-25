package com.onethefull.kotlin_coroutines.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.onethefull.kotlin_coroutines.*
import com.onethefull.kotlin_coroutines.databinding.FragmentPlantListBinding

/**
 * @author jskim
 * @email jskim@1thefull.com
 * @created 2022/10/21 5:57 오후
 * @desc
 */
class PlantListFragment: Fragment() {
    private val viewModel: PlantListViewModel by viewModels{
        Injector.providePlantListViewModelFactory(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentPlantListBinding.inflate(inflater, container, false)
        context ?: return binding.root
        viewModel.spinner.observe(viewLifecycleOwner){show ->
            binding.spinner.visibility = if (show) View.VISIBLE else View.GONE
        }

        viewModel.snackbar.observe(viewLifecycleOwner){ text ->
            text?.let {
                Snackbar.make(binding.root, text, Snackbar.LENGTH_SHORT).show()
                viewModel.onSnackbarShown()
            }
        }

        val adapter = PlantAdapter()
        binding.plantList.adapter = adapter

        return binding.root
    }

    private fun subscribeUi(adapter: PlantAdapter){
        viewModel.plants.observe(viewLifecycleOwner){ plants ->
            adapter.submitList(plants)
        }
    }

    private fun updateData(){
        with(viewModel){
            if (isFiltered()){
                clearGrowZoneNumber()
            }else{
                setGrowZoneNumber(9)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_plant_list, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.filter_zone ->{
                updateData()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}

/**
 * Factory for creating a [PlantListViewModel] with a constructor
 * that takes a [PlantRepository]/
 */
class PlantListViewModelFactory(
    private val repository: PlantRepository
): ViewModelProvider.NewInstanceFactory(){
    @Suppress("UNCHECKED_CAST")
    override fun <T: ViewModel> create(modelClass: Class<T>) = PlantListViewModel(repository) as T
}