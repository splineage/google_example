package com.onethefull.rxjavatest

import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import org.junit.Test

/**
 * @author jskim
 * @email jskim@1thefull.com
 * @created 2022/10/26 4:20 오후
 * @desc
 */
class RxJavaTest {
    @Test
    fun hello(){
        val ob = Observable
            .just("Hello?","RxJava?!")
            .map { it.dropLast(1) }

        val disposed = ob.subscribe(::println)
        disposed.dispose()
    }

    @Test
    fun observerTest(){
        val observer = object : Observer<Int>{
            override fun onSubscribe(d: Disposable) {
                println("onSubscribe ${d.toString()}")
            }

            override fun onNext(t: Int) {
                println("onNext: $t")
            }

            override fun onError(e: Throwable) {
                println(e.message)
            }

            override fun onComplete() {
                println("onComplete")
            }
        }

        Observable.just(1,2,3,4).subscribe(observer)
    }

    @Test
    fun consumerTest(){
        val disposable: Disposable = Observable.just(1,2,3,4)
            .subscribe(
                { println("onNext: $it") }, // onNext
                { println("onError: ${it.message}") }, // onError
                { println("onComplete") }, // onComplete
                { println("onSubscribe") } // onSubscribe
            )
        disposable.dispose()
    }
}