package com.onethefull.basic_lemonade

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import com.onethefull.basic_lemonade.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val LEMONADE_STATE = "LEMONADE_STATE"
    private val LEMON_SIZE = "LEMON_SIZE"
    private val SQUEEZE_COUNT = "SQUEEZE_COUNT"

    // SELECT represents the "pick lemon" state
    private val SELECT = "select"

    // SQUEEZE represents the "squeeze lemon" state
    private val SQUEEZE = "squeeze"

    // DRINK represents the "drink lemonade" state
    private val DRINK = "drink"

    // RESTART represents the state where the lemonade has been drunk and the glass is empty
    private val RESTART = "restart"

    // Default the state to select
    private var lemonadeState = "select"

    // Default lemonSize to -1
    private var lemonSize = -1

    // Default the squeezeCount to -1
    private var squeezeCount = -1
    private var lemonTree = LemonTree()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState != null) {
            lemonadeState = savedInstanceState.getString(LEMONADE_STATE, "state")
            lemonSize = savedInstanceState.getInt(LEMON_SIZE, -1)
            squeezeCount = savedInstanceState.getInt(SQUEEZE_COUNT, -1)
        }

        setUI()

        binding.imageLemonState.setOnClickListener {
            clickLemonImage()
        }
        binding.imageLemonState.setOnLongClickListener {
            showSnackbar()
            false
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(LEMONADE_STATE, lemonadeState)
        outState.putInt(LEMON_SIZE, lemonSize)
        outState.putInt(SQUEEZE_COUNT, squeezeCount)
        super.onSaveInstanceState(outState)
    }

    private fun clickLemonImage() {
        when (lemonadeState) {
            SELECT -> {
                lemonadeState = SQUEEZE
                lemonSize = lemonTree.pick()
                squeezeCount = 0
            }
            SQUEEZE -> {
                squeezeCount++
                lemonSize--
                if (lemonSize == 0) lemonadeState = DRINK
            }
            DRINK -> {
                lemonSize = -1
                lemonadeState = RESTART
            }
            RESTART -> {
                lemonadeState = SELECT
            }
        }
        setUI()
    }

    private fun setUI() {
        binding.textAction.text = lemonadeState
        var resource = 0
        when (lemonadeState) {
            SELECT -> {
                resource = R.drawable.lemon_tree
            }
            SQUEEZE -> {
                binding.textAction.text = "$lemonadeState $lemonSize"
                resource = R.drawable.lemon_squeeze
            }
            DRINK -> {
                resource = R.drawable.lemon_drink
            }
            RESTART -> {
                resource = R.drawable.lemon_restart
            }
        }
        binding.imageLemonState.setImageResource(resource)
    }

    private fun showSnackbar(): Boolean {
        if (lemonadeState != SQUEEZE) {
            return false
        }
        val squeezeText = getString(R.string.squeeze_count, squeezeCount)
        Snackbar.make(
            binding.constraintLayout,
            squeezeText,
            Snackbar.LENGTH_SHORT
        ).show()
        return true
    }
}

class LemonTree {
    fun pick(): Int {
        return (2..4).random()
    }
}