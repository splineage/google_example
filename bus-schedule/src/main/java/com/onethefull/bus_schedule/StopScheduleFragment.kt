package com.onethefull.bus_schedule

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.onethefull.bus_schedule.databinding.FragmentStopScheduleBinding
import com.onethefull.bus_schedule.viewmodels.BusScheduleViewModel
import com.onethefull.bus_schedule.viewmodels.BusScheduleViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class StopScheduleFragment : Fragment() {

    private var _binding: FragmentStopScheduleBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private lateinit var stopName: String

    private val viewModel: BusScheduleViewModel by activityViewModels{
        BusScheduleViewModelFactory(
            (activity?.application as BusScheduleApplication).database.scheduleDao()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            stopName = it.getString(STOP_NAME).toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStopScheduleBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val busStopAdapter = BusStopAdapter{}
        recyclerView.adapter = busStopAdapter
        GlobalScope.launch(Dispatchers.IO) {
            busStopAdapter.submitList(viewModel.scheduleForStopName(stopName))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object{
        var STOP_NAME = "stopName"
    }
}