package org.sdkotlin.intro.kotlin._18_dataclasses

data class KotlinPerson(var name: String, var favoriteProgrammingLanguage: String = "Kotlin") {
	var age: Int = 1
}

fun main() {
	val luke = KotlinPerson("Luke")
	println(luke)

	val (name, favoriteProgrammingLanguage) = luke
	println("Use the $favoriteProgrammingLanguage, $name!")

	val leia = luke.copy(name = "Leia")
	println(leia.age)
}
