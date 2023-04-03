package com.example.diceroller

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.diceroller.adapter.ItemAdapter
import com.example.diceroller.databinding.ActivityAffirmationsBinding
import com.example.diceroller.model.DataSource

class AffirmationsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAffirmationsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAffirmationsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val myDataset = DataSource().loadAffirmations()
        binding.recyclerView.adapter = ItemAdapter(this, myDataset)

        // Use this settings to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        binding.recyclerView.setHasFixedSize(true)
    }
}
