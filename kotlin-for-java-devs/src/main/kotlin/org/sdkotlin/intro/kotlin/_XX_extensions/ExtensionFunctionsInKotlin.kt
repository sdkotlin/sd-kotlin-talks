package org.sdkotlin.intro.kotlin._XX_extensions

import java.util.*

fun ClosedRange<Int>.random() = Random().nextInt(endInclusive - start) + start

fun <T> MutableList<T>.swap(index1: Int, index2: Int) {
	val tmp = this[index1] // 'this' corresponds to the list
	this[index1] = this[index2]
	this[index2] = tmp
}

fun main() {

	val randomNumber = (1..100).random()

	println("Here's a number between 1-100: $randomNumber")

	val list = mutableListOf(1, 2, 3)
	list.swap(0, 2)

	println("Here's the swapped list: $list")
}
