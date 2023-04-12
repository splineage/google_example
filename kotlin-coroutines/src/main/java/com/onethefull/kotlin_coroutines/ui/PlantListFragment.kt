package com.onethefull.kotlin_coroutines.ui

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.*
import com.google.android.material.snackbar.Snackbar
import com.onethefull.kotlin_coroutines.*
import com.onethefull.kotlin_coroutines.databinding.FragmentPlantListBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * @author jskim
 * @email jskim@1thefull.com
 * @created 2022/10/21 5:57 오후
 * @desc
 */
class PlantListFragment: Fragment() {
    private val viewModel: PlantListViewModel by viewModels()
    {
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

        Log.d("PlantListFragment", "observe")
        repeatOnStarted {
            viewModel.testFlow.collect{
                Log.d("PlantListFragment","observed")
                it.forEach { i ->
                    Log.d("PlantListFragment","$i")
                }
            }
        }

        val adapter = PlantAdapter()
        binding.plantList.adapter = adapter

        viewModel.test()
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

    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_plant_list, menu)
    }

    @Deprecated("Deprecated in Java")
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

fun LifecycleOwner.repeatOnStarted(block: suspend CoroutineScope.() -> Unit){
    lifecycleScope.launch{
        lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED, block)
    }
}