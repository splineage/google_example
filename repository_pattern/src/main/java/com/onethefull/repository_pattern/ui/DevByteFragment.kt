package com.onethefull.repository_pattern.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.onethefull.repository_pattern.R
import com.onethefull.repository_pattern.adapter.DevByteAdapter
import com.onethefull.repository_pattern.adapter.VideoClick
import com.onethefull.repository_pattern.databinding.FragmentDevByteBinding
import com.onethefull.repository_pattern.domain.DevByteVideo
import com.onethefull.repository_pattern.viewmodels.DevByteViewModel


/**
 * Show a list of DevBytes on screen.
 */
class DevByteFragment : Fragment() {
    /**
     * One way to delay creation of the viewModel until an appropriate lifecycle method is to use
     * lazy. This requires that viewModel not be referenced before onActivityCreated, which we
     * do in this Fragment.
     */
    private val viewModel: DevByteViewModel by lazy {
        val activity = requireNotNull(this.activity){
            "You can only access the viewModel after onActivityCreated()"
        }
        ViewModelProvider(this, DevByteViewModel.Factory(activity.application))
            .get(DevByteViewModel::class.java)
    }

    private var viewModelAdapter: DevByteAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentDevByteBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_dev_byte,
            container,
            false
        )

        // Set the lifecycleOwner so DataBinding can observe LiveData
        binding.setLifecycleOwner(viewLifecycleOwner)

        binding.viewModel = viewModel

        viewModelAdapter = DevByteAdapter(VideoClick {
            val packageManager = context?.packageManager ?: return@VideoClick
            var intent = Intent(Intent.ACTION_VIEW, it.launchUri)
            if (intent.resolveActivity(packageManager) == null){
                intent = Intent(Intent.ACTION_VIEW, Uri.parse(it.url))
            }
            startActivity(intent)
        })

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = viewModelAdapter
        }

        // Observe for the network error.
        viewModel.eventNetworkError.observe(viewLifecycleOwner, Observer<Boolean> { isNetworkError ->
            if (isNetworkError) onNetworkError()
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.playlist.observe(viewLifecycleOwner, Observer<List<DevByteVideo>>{ videos ->
            videos?.apply {
                viewModelAdapter?.videos = videos
            }

        })
    }
    /**
     * Method for displaying a Toast error message for network errors.
     */
    private fun onNetworkError(){
        if (!viewModel.isNetworkErrorShown.value!!){
            Toast.makeText(activity, "Network Error", Toast.LENGTH_LONG).show()
            viewModel.onNetworkErrorShown()
        }
    }

    /**
     * Helper method to generate YouTube app links
     */
    private val DevByteVideo.launchUri: Uri
        get(){
            val httpUri = Uri.parse(url)
            return Uri.parse("vnd.youtube:" + httpUri.getQueryParameter("v"))
        }

}