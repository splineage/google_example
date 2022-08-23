package com.test.android_hilt.navigator

import androidx.fragment.app.FragmentActivity
import com.test.android_hilt.R
import com.test.android_hilt.ui.ButtonsFragment
import com.test.android_hilt.ui.LogsFragment
import javax.inject.Inject

/**
 * @author jskim
 * @email jskim@1thefull.com
 * @created 2022/08/23 10:54 오전
 * @desc
 */
class AppNavigatorImpl @Inject constructor(private val activity: FragmentActivity): AppNavigator {
    override fun navigateTo(screens: Screens) {
        val fragment = when(screens){
            Screens.BUTTONS -> ButtonsFragment()
            Screens.LOGS -> LogsFragment()
        }
        activity.supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, fragment)
            .addToBackStack(fragment::class.java.canonicalName) //
            .commit()
    }
}