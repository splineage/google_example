package com.test.android_hilt.data

/**
 * @author jskim
 * @email jskim@1thefull.com
 * @created 2022/08/23 3:38 오후
 * @desc
 */
// Common interface for Logger data source.
interface LoggerDataSource {
    fun addLog(msg: String)
    fun getAllLogs(callback: (List<Log>) -> Unit)
    fun removeLogs()
}