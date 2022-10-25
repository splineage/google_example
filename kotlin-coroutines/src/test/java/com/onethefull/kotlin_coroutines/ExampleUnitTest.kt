package com.onethefull.kotlin_coroutines

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*
import java.util.concurrent.Executors

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
    @Test
    fun coroutineTest1(){
        runBlocking {
            launch {
                println("1")
            }
            println("2")
        }
        println("3")
    }

    @Test
    fun coroutineTest2() = runBlocking {
        launch {
            launch {
                println("1")
            }
            println("2")
        }
        println("3")
    }

    @Test
    fun coroutineTest3() = runBlocking {
        launch {
            launch {
                println("1 in ${Thread.currentThread().name}")
            }
            println("2 in ${Thread.currentThread().name}")
        }
        Thread.sleep(2000)
        println("3 in ${Thread.currentThread().name}")
    }

    @Test
    fun coroutineTest4() = runBlocking{
        launch(Dispatchers.IO){
            launch{
                println("1 in ${Thread.currentThread().name}")
            }
            println("2 in ${Thread.currentThread().name}")
        }
        Thread.sleep(2000)
        println("3 in ${Thread.currentThread().name}")
    }

    @Test
    fun coroutineTest5() = runBlocking {
        val oneThreadDispatcher = Executors.newFixedThreadPool(1)
            .asCoroutineDispatcher()
        launch(oneThreadDispatcher) {
            launch(oneThreadDispatcher) {
                println("1 in ${Thread.currentThread().name}")
            }
            println("2 in ${Thread.currentThread().name}")
        }
        println("3 in ${Thread.currentThread().name}")
    }
}