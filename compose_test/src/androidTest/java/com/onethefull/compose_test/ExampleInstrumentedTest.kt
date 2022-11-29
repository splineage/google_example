package com.onethefull.compose_test

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Rule
    @JvmField
    val composeTestRule = createAndroidComposeRule<SecondActivity>()

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.onethefull.compose_test", appContext.packageName)
    }

    @Test
    fun testGreeting(){
        val greeting = InstrumentationRegistry.getInstrumentation()
            .targetContext.resources.getString(R.string.app_name)
        composeTestRule.onNodeWithText(greeting).assertIsDisplayed()
    }
}