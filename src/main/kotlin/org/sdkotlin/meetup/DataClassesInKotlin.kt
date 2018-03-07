package org.sdkotlin.meetup

data class KotlinPerson(var name: String, var favoriteProgrammingLanguage: String = "Kotlin")

fun main(args: Array<String>) {
	val luke = KotlinPerson("Luke")
	println(luke)

	val (name, favoriteProgrammingLanguage) = luke
	println("Use the $favoriteProgrammingLanguage, $name!")

	val leia = luke.copy(name = "Leia")
	println(leia)
}
