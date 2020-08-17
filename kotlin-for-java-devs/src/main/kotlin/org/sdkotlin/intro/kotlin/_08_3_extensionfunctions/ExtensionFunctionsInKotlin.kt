package org.sdkotlin.intro.kotlin._08_3_extensionfunctions

// Kotlin allows us to add functions to an existing class by prefixing the
// function name with the type it's extending.

// You can refer to the instance the extension is called on (the "receiver")
// with the usual 'this' keyword.

fun String.shuffled() = this.toList().shuffled().joinToString(separator = "")

// This is handy for adding things to the stdlib or other libraries that you
// feel are missing.

fun String.bedazzled() = "***$this***"

fun main() {

	println("Alphabet.shuffled(): ${"Alphabet".shuffled()}")

	println("Snazzy.bedazzled(): ${"Snazzy".bedazzled()}")

	// Extension functions are resolved statically by the receiver's
	// declared or inferred type. In other words, they are not polymorphic.

	abstract class Animal

	class Lion : Animal()

	fun Animal.name() = "Animal"
	fun Lion.name() = "Simba"

	val genericLion: Animal = Lion()
	val specificLion = Lion()

	println("Lion as Animal's name: ${genericLion.name()}")
	println("Lion as Lion's name: ${specificLion.name()}")
}
