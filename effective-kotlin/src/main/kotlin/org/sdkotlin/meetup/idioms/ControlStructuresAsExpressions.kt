package org.sdkotlin.meetup.idioms

import org.json.*
import java.time.*
import java.time.Month.*

// Use `if` as an expression in place of the absent ternary operator

fun toBinary(boolean: Boolean): Byte {
	// return (boolean) ? 1 : 0 // Doesn't compile!
	return if (boolean) 1 else 0
}

// Use `when` as an expression for terse transformer functions

fun Month.toQuarter() = when (this) {
	JANUARY, FEBRUARY, MARCH -> 1
	APRIL, MAY, JUNE -> 2
	JULY, AUGUST, SEPTEMBER -> 3
	OCTOBER, NOVEMBER, DECEMBER -> 4
}

// Use `try` as an expression for setting values from functions that may throw exceptions

fun parseJson(json: String): String {
	return try {
		JSONObject(json).getString("usergroup")
	} catch (e: JSONException) {
		"Unknown"
	}
}

fun main(args: Array<String>) {

	val boolean = true
	val binary = toBinary(boolean)
	println("Binary equivalent of $boolean is $binary")

	val month = JUNE
	val quarter = month.toQuarter()
	println("$month is in quarter $quarter")

	val partialJson = "'usergroup' : 'SD Kotlin'"
	val completeJson = "{ $partialJson }"
	println("Correct JSON parses to \"${parseJson(
			completeJson)}\"")
	println("Incorrect JSON parses to \"${parseJson(
			partialJson)}\"")
}
