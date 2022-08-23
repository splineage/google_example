package com.test.android_hilt.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.android_hilt.LogApplication
import com.test.android_hilt.R
import com.test.android_hilt.navigator.AppNavigator
import com.test.android_hilt.navigator.Screens
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var navigator: AppNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigator = (applicationContext as LogApplication).serviceLocator.provideNavigator(this)

        if (savedInstanceState == null){
            navigator.navigateTo(Screens.BUTTONS)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (supportFragmentManager.backStackEntryCount == 0){
            finish()
        }
    }
}