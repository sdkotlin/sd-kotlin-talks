package org.sdkotlin.intro.kotlin._04_imports

// Imports in Kotlin are very similar to Java.

import java.math.BigDecimal
import kotlin.math.PI

// If there is a name collision, the 'as' keyword can be used.

import org.sdkotlin.intro.kotlin._04_imports.otherpackage.Thing1 as Thing2

class Thing1

var lawOfLargeNumbers = BigDecimal.TEN

var thing1 = Thing1()
var thing2 = Thing2()

fun main() {
	println(PI)
	println(lawOfLargeNumbers)
	println("Thing1 type: " + thing1::class.simpleName)
	println("Thing2 type: " + thing2::class.simpleName)
}
