package org.sdkotlin.intro.kotlin._00_toplevel

// You may define top level variables and functions in Kotlin

var sanDiego = true

fun printWeather() {
	if (sanDiego) {
		println("It's sunny!")
	}
}

fun main() {
	printWeather()
}
