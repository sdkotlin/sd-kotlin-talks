package org.sdkotlin.intro.kotlin._09_ranges

import java.time.LocalDate

fun main() {

	// Kotlin supports ranges, which are start and end inclusive.

	val oneThroughTen = 0..10

	val longRange = 0L..10L

	val charRange = 'a'..'z'

	val floatRange = 1.0f..10.5f

	val doubleRange = 1.0..10.5

	// Any type that implements Comparable can be ranged.

	val dateRange = LocalDate.of(2020, 1, 1)..LocalDate.of(2020, 12, 31)

	// Ranges have a useful toString().

	println("charRange.toString(): $charRange")

	// Values can be tested for inclusion in ranges with 'in' and '!in'

	println("10 in $oneThroughTen: ${10 in oneThroughTen}")
	println("11 not in $oneThroughTen: ${11 !in oneThroughTen}")
	println("a in $charRange: ${'a' in charRange}")

	println("2020-06-01 in $dateRange: "
			+ (LocalDate.of(2020, 6, 1) in dateRange))

	// You can get the start and end of a range, and whether it is empty.

	println("dateRange.start: ${dateRange.start}")
	println("dateRange.endInclusive: ${dateRange.endInclusive}")
	println("Backwards ranges (e.g. 1..0) are empty: ${(1..0).isEmpty()}")

	// Integral ranges are also progressions, which are iterable and have other
	// additionally useful properties.

	// You can get an end-exclusive integral progression with 'until'.

	val exclusiveIntegralProgression = 0 until 10 // 0-9

	// Integral progressions can go down.

	val reverseIntegralProgression = 10 downTo 1

	// Integral progressions can have steps.

	val evenNumbers = 2..20 step 2

	val oddNumbersBackwards = 19 downTo 1 step 2

	// Integral ranges also have handy extension functions defined on them.

	val randomNumber = oneThroughTen.random()

	println("I'm thinking of a number from $oneThroughTen. It's $randomNumber.")
}
