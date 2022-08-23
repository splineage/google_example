package com.test.android_hilt.util

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

/**
 * @author jskim
 * @email jskim@1thefull.com
 * @created 2022/08/23 10:33 오전
 * @desc
 */
class DateFormatter {
    @SuppressLint("SimpleDateFormat")
    private val formatter = SimpleDateFormat("d MMM yyyy HH:mm:ss")

    fun formatDate(timestamp: Long): String {
        return formatter.format(Date(timestamp))
    }
}