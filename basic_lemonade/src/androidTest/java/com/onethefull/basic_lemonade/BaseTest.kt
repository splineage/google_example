package com.onethefull.basic_lemonade

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.onethefull.basic_lemonade.DrawableMatcher.withDrawable
import org.hamcrest.Description
import org.hamcrest.Matcher

/**
 * @author jskim
 * @email jskim@1thefull.com
 * @created 2022/11/11 1:06 오전
 * @desc
 */
open class BaseTest {
    fun testState(textActionResource: Int, drawableResource: Int) {
        onView(withId(R.id.text_action))
            .check(matches(ViewMatchers.withText(textActionResource)))
        onView(withId(R.id.image_lemon_state)).check(
            matches(withDrawable(drawableResource))
        )
    }

    fun pickLemon(){
        onView(withDrawable(R.drawable.lemon_tree))
            .perform(click())
    }

    fun juiceLemon(){
        while (onView(withDrawable(R.drawable.lemon_squeeze)).isPresent()){
            onView(withId(R.id.image_lemon_state)).perform(click())
        }
    }

    fun restart(){
        onView(withDrawable(R.drawable.lemon_restart))
            .perform(click())
    }
    /**
     * Extension function to determine if element is present.
     */
    private fun ViewInteraction.isPresent(): Boolean{
        return try {
            check(matches(isDisplayed()))
            true
        }catch (e: NoMatchingViewException){
            false
        }
    }
}

/**
 * Custom matcher to find drawable
 */
object DrawableMatcher {
    fun withDrawable(@DrawableRes resourceId: Int): Matcher<View> {
        return object : BoundedMatcher<View, ImageView>(ImageView::class.java) {
            override fun describeTo(description: Description?) {
                description!!.appendText("has drawable resource $resourceId")
            }

            override fun matchesSafely(imageView: ImageView): Boolean {
                return isSameBitmap(imageView, imageView.drawable, resourceId)
            }
        }
    }

    private fun isSameBitmap(item: View, drawable: Drawable?, expectedResId: Int): Boolean {
        val image = item as ImageView
        if (expectedResId < 0) {
            return image.drawable == null
        }
        val expectedDrawable: Drawable? = ContextCompat.getDrawable(item.context, expectedResId)
        if (drawable == null || expectedDrawable == null) {
            return false
        }
        val bitmap = getBitmap(drawable)
        val expectedBitmap = getBitmap(expectedDrawable)
        return bitmap.sameAs(expectedBitmap)
    }

    /**
     * Convert vector drawable to bitmap.
     */
    private fun getBitmap(drawable: Drawable): Bitmap {
        val bitmap = Bitmap.createBitmap(
            drawable.intrinsicWidth,
            drawable.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)
        return bitmap
    }
}