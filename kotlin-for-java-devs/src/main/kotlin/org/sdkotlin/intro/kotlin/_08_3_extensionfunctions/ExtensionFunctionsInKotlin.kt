package org.sdkotlin.intro.kotlin._08_3_extensionfunctions

// Kotlin allows us to add functions to extend an existing class.

fun String.shuffled() = this.toList().shuffled().joinToString(separator = "")

fun main() {
	println("Alphabet".shuffled())
}
