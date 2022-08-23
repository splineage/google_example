package com.test.android_hilt.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.test.android_hilt.LogApplication
import com.test.android_hilt.R
import com.test.android_hilt.data.Log
import com.test.android_hilt.data.LoggerDataSource
import com.test.android_hilt.data.LoggerLocalDataSource
import com.test.android_hilt.di.InMemoryLogger
import com.test.android_hilt.util.DateFormatter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * @author jskim
 * @email jskim@1thefull.com
 * @created 2022/08/23 10:53 오전
 * @desc
 */
@AndroidEntryPoint
class LogsFragment: Fragment() {

    @InMemoryLogger
    @Inject
    lateinit var logger: LoggerDataSource

    @Inject
    lateinit var dateFormatter: DateFormatter

    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_logs, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view).apply {
            setHasFixedSize(true)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        populateFields(context)
    }

    private fun populateFields(context: Context){
//        logger = (context.applicationContext as LogApplication).serviceLocator.loggerLocalDataSource
//        dateFormatter = (context.applicationContext as LogApplication).serviceLocator.provideDateFormatter()
    }

    override fun onResume() {
        super.onResume()
        logger.getAllLogs { logs ->
            recyclerView.adapter =
                LogsViewAdapter(
                    logs,
                    dateFormatter
                )
        }
    }
}

private class LogsViewAdapter(
    private val logsDataSet: List<Log>,
    private val dateFormatter: DateFormatter
): RecyclerView.Adapter<LogsViewAdapter.LogsViewHolder>(){
    class LogsViewHolder(val textView: TextView): RecyclerView.ViewHolder(textView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LogsViewHolder {
        return LogsViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.text_row_item, parent, false) as TextView
        )
    }

    override fun getItemCount(): Int {
        return logsDataSet.size
    }

    override fun onBindViewHolder(holder: LogsViewHolder, position: Int) {
        val log = logsDataSet[position]
        holder.textView.text = "${log.msg}\n\t${dateFormatter.formatDate(log.timestamp)}"
    }
}