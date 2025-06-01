package org.sdkotlin.integral

interface Integral<I> : Comparable<I> where I : Comparable<I> {

	val minValue: I
	val maxValue: I
	val zero: I
	val one: I

	operator fun plus(other: I): I
	operator fun minus(other: I): I
	operator fun unaryMinus(): I
	operator fun rem(other: I): I
}
