package com.example.diceroller

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.diceroller.databinding.ActivityLemonadeBinding
import com.google.android.material.snackbar.Snackbar

class LemonadeActivity: AppCompatActivity() {
    lateinit var binding: ActivityLemonadeBinding
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
        binding = ActivityLemonadeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // DO NOT ALTER THE CODE IN THE FOLLOWING IF STATEMENT
        if (savedInstanceState != null){
            lemonadeState = savedInstanceState.getString(LEMONADE_STATE, "select")
            lemonSize = savedInstanceState.getInt(LEMON_SIZE, -1)
            squeezeCount = savedInstanceState.getInt(SQUEEZE_COUNT, -1)
        }

        setViewElements()
        binding.imageView.setOnClickListener {
            clickLemonImage()
        }
        binding.imageView.setOnLongClickListener {
            showSnackbar()
            false
        }

    }

    /**
     * This method saves the state of the app if it is put in the background.
     */
    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(LEMONADE_STATE, lemonadeState)
        outState.putInt(LEMON_SIZE, lemonSize)
        outState.putInt(SQUEEZE_COUNT, squeezeCount)
        super.onSaveInstanceState(outState)
    }

    private fun clickLemonImage(){
        when(lemonadeState){
            SELECT->{
                lemonadeState = SQUEEZE
                binding.imageView.setImageResource(R.drawable.lemon_squeeze)
                binding.textView.text = resources.getString(R.string.lemon_squeeze)
                squeezeCount = lemonTree.pick()
            }
            SQUEEZE->{
                if (squeezeCount < 0){
                    lemonadeState = DRINK
                    binding.imageView.setImageResource(R.drawable.lemon_drink)
                    binding.textView.text = resources.getString(R.string.lemon_drink)
                }else{
                    squeezeCount--
                }
            }
            DRINK->{
                lemonadeState = RESTART
                binding.imageView.setImageResource(R.drawable.lemon_restart)
                binding.textView.text = resources.getString(R.string.lemon_empty_glass)
            }
            RESTART->{
                lemonadeState = SELECT
                binding.imageView.setImageResource(R.drawable.lemon_tree)
                binding.textView.text = resources.getString(R.string.lemon_select)
            }
        }

    }

    /**
     * Set up the view elements according to the state.
     */
    private fun setViewElements(){
        binding.imageView.setImageResource(R.drawable.lemon_squeeze)
        binding.textView.text = resources.getString(R.string.lemon_squeeze)
    }

    /**
     * Long clicking the lemon image will show how many times the lemon has been sqeezed.
     */
    private fun showSnackbar(): Boolean{
        if (lemonadeState != SQUEEZE){
            return false
        }
        val squeezeText = getString(R.string.squeeze_count, squeezeCount)
        Snackbar.make(
            binding.root,
            squeezeText,
            Snackbar.LENGTH_SHORT
        ).show()
        return true
    }
}

class LemonTree{
    fun pick(): Int{
        return (2..4).random()
    }
}
