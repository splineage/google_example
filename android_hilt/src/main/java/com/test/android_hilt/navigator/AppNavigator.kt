package com.test.android_hilt.navigator

/**
 * @author jskim
 * @email jskim@1thefull.com
 * @created 2022/08/23 10:54 오전
 * @desc
 */
enum class Screens{
    BUTTONS,
    LOGS
}
interface AppNavigator {
    fun navigateTo(screens: Screens)
}