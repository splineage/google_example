package com.onethefull.bus_schedule

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.onethefull.bus_schedule.databinding.FragmentFullScheduleBinding
import com.onethefull.bus_schedule.viewmodels.BusScheduleViewModel
import com.onethefull.bus_schedule.viewmodels.BusScheduleViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class FullScheduleFragment : Fragment() {
    private var _binding: FragmentFullScheduleBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView

    private val viewModel: BusScheduleViewModel by activityViewModels{
        BusScheduleViewModelFactory(
            (activity?.application as BusScheduleApplication).database.scheduleDao()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFullScheduleBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val busStopAdapter = BusStopAdapter{
            val action = FullScheduleFragmentDirections.actionFullScheduleFragmentToStopScheduleFragment(
                stopName = it.stopName
            )
            view.findNavController().navigate(action)
        }
        recyclerView.adapter = busStopAdapter
        // Using GlobalScope is not best practice, and in the next
        // stop we'll see how to improve this.
        GlobalScope.launch(Dispatchers.IO) {
            busStopAdapter.submitList(viewModel.fullSchedule())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}