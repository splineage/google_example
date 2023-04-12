package com.onethefull.compose_test

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.onethefull.compose_test.ui.theme.DiceRollerTheme

/**
 * @author jskim
 * @email jskim@1thefull.com
 * @created 2023/04/12 10:43 AM
 * @desc
 */
class ProfileActivity: ComponentActivity() {
    private val puppy: Puppy by lazy {
        intent?.getSerializableExtra(PUPPY_ID) as Puppy
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiceRollerTheme{
                ProfileScreen(puppy)
            }
        }
    }

    companion object{
        private const val PUPPY_ID = "puppy_id"
        fun newIntent(context: Context, puppy: Puppy) = Intent(context, ProfileActivity::class.java).apply {
            putExtra(PUPPY_ID, puppy)
        }
    }
}