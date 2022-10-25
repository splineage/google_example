package com.onethefull.kotlin_coroutines

/**
 * @author jskim
 * @email jskim@1thefull.com
 * @created 2022/10/24 10:10 오전
 * @desc
 */

sealed class NetworkResult<out T>

// By using Nothing as T, Loading is a subtype of all NetworkResult<T>
object Loading: NetworkResult<Nothing>()

// Successful results are stored in data
data class OK<out T>(val data: T): NetworkResult<T>()

// By using Nothing as T, all NetworkError instances are a subtypes of all NetworkResults<T>
data class NetworkError(val exception: Throwable): NetworkResult<Nothing>()
