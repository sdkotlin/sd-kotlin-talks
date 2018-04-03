package org.sdkotlin.meetup

// IterationInKotlin
fun main(args: Array<String>) {

	for (i in 1..5) {
		println("Counting... $i")
	}

	(1..5).forEach { i -> println("Again... $i") }

	(1 until 5).forEach { i -> println("And again... $i") }

	repeat(5) { i -> println("Yet again... $i") }

	for (i in 5 downTo 1) {
		println("Down... $i")
	}

	for (i in 1..5 step 2) {
		println("Odds... #$i")
	}

	val alphabet = ('a'..'z').toList()

	for (l in alphabet) {
		println("Letters... $l")
	}

	for (i in alphabet.indices) {
		println("Letter ${i + 1} of the alphabet is ${alphabet[i]}")
	}

	val oneToFive = 1..5
	var i = oneToFive.start

	while (i in oneToFive) {
		println("Counting between ${oneToFive.start} and ${oneToFive.endInclusive}: ${i++}")
	}
}
