package org.sdkotlin.intro.kotlin._03_toplevel

// You can define top level properties and functions in Kotlin.

var sanDiego = true

// They can be private, in which case they're only visible within the same file.

private var justHere = true

fun printWeather() {

	if (sanDiego) {
		println("It's sunny!")
	}

	println(justHere)
}
