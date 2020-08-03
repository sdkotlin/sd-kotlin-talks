package org.sdkotlin.intro.kotlin._03_toplevel

// You may define top level variables and functions in Kotlin

var sanDiego = true

private var justHere = true

fun printWeather() {
	if (sanDiego) {
		println("It's sunny!")
	}

	println(justHere)
}
