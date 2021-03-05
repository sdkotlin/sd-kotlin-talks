package org.sdkotlin.meetup.idioms

import org.json.JSONException
import org.json.JSONObject
import java.time.Month
import java.time.Month.APRIL
import java.time.Month.AUGUST
import java.time.Month.DECEMBER
import java.time.Month.FEBRUARY
import java.time.Month.JANUARY
import java.time.Month.JULY
import java.time.Month.JUNE
import java.time.Month.MARCH
import java.time.Month.MAY
import java.time.Month.NOVEMBER
import java.time.Month.OCTOBER
import java.time.Month.SEPTEMBER

// Use `if` as an expression in place of the absent ternary operator.

fun toBinary(boolean: Boolean): Byte {
	// return (boolean) ? 1 : 0 // Doesn't compile!
	return if (boolean) 1 else 0
}

// Use `when` as an expression for terse transformer functions.

fun Month.toQuarter() = when (this) {
	JANUARY, FEBRUARY, MARCH -> 1
	APRIL, MAY, JUNE -> 2
	JULY, AUGUST, SEPTEMBER -> 3
	OCTOBER, NOVEMBER, DECEMBER -> 4
}

// Use `try` as an expression for setting values from functions that may throw
// exceptions.

fun parseJson(json: String): String {
	return try {
		JSONObject(json).getString("usergroup")
	} catch (e: JSONException) {
		"Unknown"
	}
}

fun main() {

	val boolean = true
	val binary = toBinary(boolean)
	println("Binary equivalent of $boolean is $binary")

	val month = JUNE
	val quarter = month.toQuarter()
	println("$month is in quarter $quarter")

	val partialJson = "'usergroup' : 'SD Kotlin'"
	val completeJson = "{ $partialJson }"
	println("Correct JSON parses to \"${parseJson(completeJson)}\"")
	println("Incorrect JSON parses to \"${parseJson(partialJson)}\"")
}
