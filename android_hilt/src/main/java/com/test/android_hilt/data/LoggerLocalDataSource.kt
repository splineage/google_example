package com.test.android_hilt.data

import android.os.Handler
import android.os.Looper
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author jskim
 * @email jskim@1thefull.com
 * @created 2022/08/23 10:22 오전
 * @Data Manager class that handles data manipulation(조작) between the database and the UI.
 */
@Singleton
class LoggerLocalDataSource @Inject constructor(private val logDao: LogDao) {
    private val executorService: ExecutorService = Executors.newFixedThreadPool(4)
    private val mainThreadHandler by lazy {
        Handler(Looper.getMainLooper())
    }

    fun addLog(msg: String){
        executorService.execute{
            logDao.insertAll(
                Log(
                    msg,
                    System.currentTimeMillis()
                )
            )
        }
    }

    fun getAllLogs(callback: (List<Log>)-> Unit){
        executorService.execute{
            val logs = logDao.getAll()
            mainThreadHandler.post{callback(logs)}
        }
    }

    fun removeLogs(){
        executorService.execute {
            logDao.nukeTable()
        }
    }
}