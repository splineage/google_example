package com.onethefull.basic_lemonade

import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.longClick
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * @author jskim
 * @email jskim@1thefull.com
 * @created 2022/11/11 1:19 오전
 * @desc
 */

@RunWith(AndroidJUnit4::class)
class LemonadeTests: BaseTest() {
    @Before
    fun setup(){
        launchActivity<MainActivity>()
    }

    /**
     * Test the view components of the pick lemon state
     */
    @Test
    fun `test_initial_state`(){
        testState(R.string.lemon_select, R.drawable.lemon_tree)
    }

    /**
     * Test that the pick lemon functionality takes us to the squeeze state
     */
    @Test
    fun `test_picking_lemon_proceeds_to_squeeze_state`(){
        onView(withId(R.id.image_lemon_state)).perform(click())
        testState(R.string.lemon_squeeze, R.drawable.lemon_squeeze)
    }

    @Test
    fun `test_squeezing_lemon_proceeds_to_drink_state`(){
        onView(withId(R.id.image_lemon_state)).perform(click())
        juiceLemon()
        testState(R.string.lemon_drink, R.drawable.lemon_drink)
    }

    @Test
    fun `test_squeeze_count_snackbar_is_displayed`(){
        onView(withId(R.id.image_lemon_state)).perform(click())
        onView(withId(R.id.image_lemon_state)).perform(click())
        onView(withId(R.id.image_lemon_state)).perform(longClick())
        onView(withId(com.google.android.material.R.id.snackbar_text))
            .check(matches(withText("Squeeze count: 1, keep squeezing!")))
    }

    @Test
    fun `test_drinking_juice_proceeds_to_restart_state`(){
        onView(withId(R.id.image_lemon_state)).perform(click())
        juiceLemon()
        onView(withId(R.id.image_lemon_state)).perform(click())
        testState(R.string.lemon_empty_glass, R.drawable.lemon_restart)
    }

    @Test
    fun `test_restarting_proceeds_to_pick_lemon_state`(){
        onView(withId(R.id.image_lemon_state)).perform(click())
        juiceLemon()
        onView(withId(R.id.image_lemon_state)).perform(click())
        onView(withId(R.id.image_lemon_state)).perform(click())
        testState(R.string.lemon_select, R.drawable.lemon_tree)
    }



















}