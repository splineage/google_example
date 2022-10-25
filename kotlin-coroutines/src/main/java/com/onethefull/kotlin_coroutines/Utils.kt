package com.onethefull.kotlin_coroutines

/**
 * @author jskim
 * @email jskim@1thefull.com
 * @created 2022/10/24 10:04 오전
 * @desc
 */

data class ComparablePair<A: Comparable<A>, B: Comparable<B>>(
    val first: A,
    val second: B
): Comparable<ComparablePair<A, B>>{
    override fun compareTo(other: ComparablePair<A, B>): Int {
        val firstComp = this.first.compareTo(other.first)
        if (firstComp != 0) return firstComp
        return this.second.compareTo(other.second)
    }
}