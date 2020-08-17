package org.sdkotlin.intro.kotlin._08_3_extensionfunctions

fun main() {

	// Kotlin allows us to add functions to an existing class.

	fun String.shuffled() = this.toList().shuffled().joinToString(separator = "")

	println("Alphabet".shuffled())

	// Extension functions are not polymorphic.

	abstract class Animal

	class Lion : Animal()

	fun Animal.name() = "Animal"
	fun Lion.name() = "Simba"

	val genericLion: Animal = Lion()
	val specificLion = Lion()

	println("Lion as Animal's name: ${genericLion.name()}")
	println("Lion as Lion's name: ${specificLion.name()}")
}
