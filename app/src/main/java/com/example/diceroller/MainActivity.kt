package com.example.diceroller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.diceroller.databinding.ActivityMainBinding

/**
 * This activity allows the user to roll a dice and view the result
 * on the screen.
 */
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnRoll.setOnClickListener {
            Toast.makeText(this, "Dice Rolled!", Toast.LENGTH_SHORT).show()
            rollDice()
        }
    }

    private fun rollDice() {
        val dice = Dice(6)
        val result = dice.roll()
        binding.textView.text = result.toString()
        when(result){
            1->{binding.imageView.setImageResource(R.drawable.dice_1)}
            2->{binding.imageView.setImageResource(R.drawable.dice_2)}
            3->{binding.imageView.setImageResource(R.drawable.dice_3)}
            4->{binding.imageView.setImageResource(R.drawable.dice_4)}
            5->{binding.imageView.setImageResource(R.drawable.dice_5)}
            6->{binding.imageView.setImageResource(R.drawable.dice_6)}
        }
    }

}

class Dice(val sides: Int) {
    /**
     * Roll the dice and update the screen with the result.
     */
    fun roll(): Int {
        return (1..sides).random()
    }
}