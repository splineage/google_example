package com.onethefull.kotlin_coroutines

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.supervisorScope
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

/**
 * @author jskim
 * @email jskim@1thefull.com
 * @created 2022/10/24 2:51 오후
 * @desc
 */

/**
 * Cache the first non-error result from an async computation passed as [block].
 *
 * Usage:
 * val cachedSuccess: CacheOnSuccess<Int> = CacheOnSuccess(onErrorFallback = {3}){
 *  delay(1_000) // compute value using coroutines
 *      5
 * }
 *
 * cachedSuccess.getOrAwait() // get the result from the cache, calling [block],
 * or fallback on exception
 *
 * @param onErrorFallback: Invoke this if [block] throws exception other than cancellation,
 * the result of this lambda will be returned for this call to [getOrAwait] but will note be
 * cached for future calls to [getOrAwait]
 * @param block Suspending lambda that produces the cached value. The first non-exceptional
 * value returned by [block] will b cached, and future calls to [getOrAwait] will return the
 * cached value or throw a [CancellationException].
 *
 */
class CacheOnSuccess<T: Any>(
    private val onErrorFallback: (suspend () -> T)? = null,
    private val block: suspend () -> T
) {
    private val mutex = Mutex()

    @Volatile
    private var deferred: Deferred<T>? = null

    suspend fun getOrAwait(): T{
        return supervisorScope {
            val currentDeferred = mutex.withLock {
                deferred?.let { return@withLock it }
                async {
                    block()
                }.also {
                    deferred = it
                }
            }
            currentDeferred.safeAwait()
        }
    }

    private suspend fun Deferred<T>.safeAwait(): T{
        try {
            return await()
        }catch (throwable: Throwable){
            mutex.withLock {
                if (deferred == this){
                    deferred = null
                }
            }

            if (throwable is CancellationException){
                throw throwable
            }
            onErrorFallback?.let { fallback -> return fallback() }
            throw throwable
        }
    }
}