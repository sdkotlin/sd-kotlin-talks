package org.sdkotlin.meetup.idioms

// Use data classes for "multiple return values"

data class Point3D(val x: Double = 0.0, val y: Double = 0.0,
		val z: Double = 0.0)

fun getOrigin() = Point3D()

fun printOrigin() {
	val (x, y, z) = getOrigin()
	println("x: $x, y: $y, z: $z")
}

// Use destructuring with Maps and Arrays

val arrayOfMonths = arrayOf("Jan.", "Feb.", "etc.")
val mapOfWeekdays = mapOf(1 to "Sunday", 2 to "Monday", 3 to "etc.")

fun printMonthsAndWeekdays() {

	for ((index, value) in arrayOfMonths.withIndex()) {
		println("Month #${index + 1} is $value")
	}

	for ((key, value) in mapOfWeekdays) {
		println("Weekday #$key is $value")
	}
}

fun main(args: Array<String>) {
	printOrigin()
	printMonthsAndWeekdays()
}
