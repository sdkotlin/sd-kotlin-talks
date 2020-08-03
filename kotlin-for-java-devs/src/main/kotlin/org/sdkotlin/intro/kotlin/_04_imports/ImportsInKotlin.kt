package org.sdkotlin.intro.kotlin._04_imports

// Imports in Kotlin are very similar to Java.

// If there is a name collision the 'as' keyword can be used.

import java.math.BigDecimal
import kotlin.math.PI
import org.sdkotlin.intro.kotlin._04_imports.otherpackage.Thing1 as Thing2

var lawOfLargeNumbers = BigDecimal.TEN

var thing1 = Thing1()
var thing2 = Thing2()

fun main() {
	println(PI)
	println(lawOfLargeNumbers)
	println("Thing1 type: " + thing1::class.simpleName)
	println("Thing2 type: " + thing2::class.simpleName)
}
