package org.sdkotlin.intro.kotlin._09_ranges

import java.time.LocalDate

fun main() {

	// Kotlin supports ranges, which are start and end inclusive.

	val oneThroughTen: IntRange = 1..10

	val longRange: LongRange = 0L..10L

	val charRange: CharRange = 'a'..'z'

	val floatRange: ClosedFloatingPointRange<Float> = 1.0f..10.5f

	val doubleRange: ClosedFloatingPointRange<Double> = 1.0..10.5

	// Ranges implement the 'ClosedRange<T: Comparable<T>>' interface.

	// Any type that implements 'Comparable' can be ranged.

	val dateRange: ClosedRange<LocalDate> = LocalDate.of(2020, 1, 1)..LocalDate.of(2020, 12, 31)

	// You can get the start and end of a range, and whether it is empty.

	println("oneThroughTen.start: ${oneThroughTen.start}")

	println("oneThroughTen.endInclusive: ${oneThroughTen.endInclusive}")

	println("Backwards ranges (e.g. 1..0) are empty: ${(1..0).isEmpty()}")

	// Values can be tested for inclusion in ranges with 'in' and '!in'

	println("10 in $oneThroughTen: ${10 in oneThroughTen}")

	println("11 not in $oneThroughTen: ${11 !in oneThroughTen}")

	println("a in $charRange: ${'a' in charRange}")

	println(
		"2020-06-01 in $dateRange: "
				+ (LocalDate.of(2020, 6, 1) in dateRange)
	)

	// Integral, floating point, and comparable ranges have a useful
	// 'equals(...)', 'hashcode()', and 'toString()' implementations.

	println("'a'..'c' == 'a'..'c': ${'a'..'c' == 'a'..'c'}")
	println("'a'..'c' == 'a'..'b': ${'a'..'c' == 'a'..'b'}")

	println("oneThroughTen.hashCode(): ${oneThroughTen.hashCode()}")

	println("dateRange.toString(): $dateRange")

	// You can get an end-exclusive integral range with 'until'.

	val openEndedIntRange: IntRange = 0 until 10 // 0-9

	// Kotlin 1.8 did bring a new open-ended range operator.

	val openEndedIntRangeOperator: IntRange = 0..<10

	// It doesn't work for floating point or generic comparable ranges as
	// there is no inherent notion of what "one less" is for them.

	//val openEndedDoubleRange = 1.0 until 2.0
	//val openEndedDateRange =
	//		LocalDate.of(2020, 1, 1) until LocalDate.of(2020, 12, 31)

	// Integral ranges are also "Progressions".

	// Progressions extend 'Iterable' for the type, and have a 'first', 'last',
	// and 'step' property.

	val oneThroughTenIterator = oneThroughTen.iterator()

	println("oneThroughTen.first: ${oneThroughTen.first}")
	println("oneThroughTen.last: ${oneThroughTen.last}")
	println("oneThroughTen.step: ${oneThroughTen.step}")

	// There is no base 'Progression' interface. They are considered "traits"
	// in that they implement these things by convention.

	// Integral progressions can go down.

	val reverseIntegralProgression = 10 downTo 1

	// Integral progressions can have steps.

	val evenNumbers = 2..20 step 2

	val oddNumbersBackwards = 19 downTo 1 step 2

	// Note that when using 'downTo' and 'step' you just get an integral
	// progression back, not an integral range (which combines the 'ClosedRange'
	// interface and the Progression trait).

	// This means you lose the 'start' and 'endInclusive' properties from
	// 'ClosedRange'.

	//evenNumbers.start
	//evenNumbers.endInclusive

	// They do still define an 'isEmpty()' for progressions.

	println("$evenNumbers.isEmpty(): ${evenNumbers.isEmpty()}")

	// Progressions still have 'contains(...)', as it's an extension function
	// for 'Iterable' and progressions are iterables.

	println("$evenNumbers.contains(4): ${evenNumbers.contains(4)}")

	// As 'contains(...)' is the overload function for the 'in' and '!in'
	// operators, they in turn are still supported for progressions.

	println("3 in $evenNumbers: ${3 in evenNumbers}")

	// As iterables, progressions have many handy extension functions.

	println("$oneThroughTen.average(): ${oneThroughTen.average()}")

	// Integral ranges also have a handy 'random()' extension function that
	// returns a random value from the range.

	val randomNumber = oneThroughTen.random()

	println("I'm thinking of a number from $oneThroughTen. It's $randomNumber.")

	// If you want the combination of 'ClosedRange' and the Progression trait
	// for types other than the included integral ones, for example for
	// 'LocalDateTime', they can be implemented without much trouble. You just
	// need to decide what the notion of a step is for that type.
}
