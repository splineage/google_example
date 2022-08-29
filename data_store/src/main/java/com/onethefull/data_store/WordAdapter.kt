package com.onethefull.data_store

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.accessibility.AccessibilityNodeInfo
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView

/**
 * @author jskim
 * @email jskim@1thefull.com
 * @created 2022/08/26 6:18 오후
 * @desc
 */
class WordAdapter(private val letterId: String, context: Context)
    : RecyclerView.Adapter<WordAdapter.WordViewHolder>()
{
    private val filteredWords: List<String>

    init {
        val words = context.resources.getStringArray(R.array.words).toList()
        filteredWords = words
            .filter { it.startsWith(letterId, ignoreCase = true) }
            .shuffled()
            .take(5)
            .sorted()
    }

    class WordViewHolder(val view: View): RecyclerView.ViewHolder(view){
        val button = view.findViewById<Button>(R.id.button_item)
    }

    override fun getItemCount(): Int {
        return filteredWords.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val layout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_view, parent, false)

        layout.accessibilityDelegate = Accessibility
        return WordViewHolder(layout)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val item = filteredWords[position]
        val context = holder.view.context
        holder.button.text = item
        holder.button.setOnClickListener {
            val queryUrl: Uri = Uri.parse("${WordListFragment.SEARCH_PREFIX}${item}")
            val intent = Intent(Intent.ACTION_VIEW, queryUrl)
            context.startActivity(intent)
        }
    }

    companion object Accessibility: View.AccessibilityDelegate(){
        override fun onInitializeAccessibilityNodeInfo(host: View?, info: AccessibilityNodeInfo?) {
            super.onInitializeAccessibilityNodeInfo(host, info)
            val customString = host?.context?.getString(R.string.look_up_word)
            val customClick =
                AccessibilityNodeInfo.AccessibilityAction(
                    AccessibilityNodeInfo.ACTION_CLICK,
                    customString
                )
            info?.addAction(customClick)
        }
    }
}