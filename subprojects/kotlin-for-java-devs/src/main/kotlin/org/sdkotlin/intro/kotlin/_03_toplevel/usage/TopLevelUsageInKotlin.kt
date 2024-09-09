package org.sdkotlin.intro.kotlin._03_toplevel.usage

// Top level variables and functions are package scoped...

import org.sdkotlin.intro.kotlin._03_toplevel.printWeather
import org.sdkotlin.intro.kotlin._03_toplevel.sanDiego

// Can't access private top-level properties or functions
//import org.sdkotlin.intro.kotlin._03_toplevel.justHere

fun main() {
	println(sanDiego)
	printWeather()
}
