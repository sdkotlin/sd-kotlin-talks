package org.sdkotlin.intro.kotlin._14_0_dataclasses

// Kotlin supports 'data' classes.

data class ProgrammingLanguage(val name: String)

data class Person(val name: String, val favoriteLanguage: String = "Kotlin") {

	// Properties can be immutable `val` (yay!) or mutable `var` (booo!).

	var age: Int = 1

	// Properties declared in the class body instead of primary constructor are
	// also included in the generated methods.
}

data class PolyglotPerson(
	val name: String,
	// All types are supported, including other data classes and collections.

	// Mutable collections such as `Array` are discouraged as they call for
	// equals and hashCode to be overridden.

	val favoriteLanguages: List<ProgrammingLanguage>,
)

lateinit var duke: Person
lateinit var daisy: Person
lateinit var doris: Person

fun `with equals, hashCode, and toString`() {

	// They include autogenerated 'equals(...)', 'hashCode()', and 'toString()'
	// overrides for all the properties defined in the primary constructor.

	duke = Person(name = "Duke", favoriteLanguage = "Java")
	daisy = Person("Daisy")

	println("Duke == Duke: ${duke == duke}")
	println("Duke == Daisy: ${duke == daisy}")

	println("duke: $duke")
	println("daisy: $daisy")

	// Properties defined outside the primary constructor are not included in
	// the implementations of those overrides.

	val olderDuke = Person("Duke", "Java")
	olderDuke.age++

	println("Duke == Older Duke: ${duke == olderDuke}")
}

fun `with component and copy functions`() {

	// Data classes also include 'componentN()' functions for all the
	// constructor-defined properties, which allows them to be used with
	// destructuring declarations.

	val (daisyName, daisyFavoriteLanguage) = daisy

	println("$daisyName loves $daisyFavoriteLanguage")

	// Data classes have a copy function with named and defaulted arguments for
	// all the constructor-defined properties. This allows you to clone an
	// instance of a data class overriding only selective properties.

	doris = daisy.copy(name = "Doris")

	println(doris)
}

fun `with data classes in the standard library`() {

	// The standard library comes with two handy data classes, 'Pair' and
	// 'Triple'. There is also a 'to' infix function for constructing Pairs.

	val couple: Pair<Person, Person> = duke to daisy

	println("couple: $couple")

	// For 'Triple' you just have to use the regular constructor.

	val family: Triple<Person, Person, Person> = Triple(duke, daisy, doris)

	println("family: $family")
}

// Data classes can have private constructors.

// But they expose equivalent construction via the generate copy method:
// https://youtrack.jetbrains.com/issue/KT-11914
@Suppress("DataClassPrivateConstructor")
data class ControlledInitialization private constructor(val onlyICanSetThis: String) {
	companion object {
		fun staticFactoryMethod(): ControlledInitialization =
			ControlledInitialization(onlyICanSetThis = "Secure")
	}
}

fun `with private data class constructor`() {

	val controlledInit = ControlledInitialization.staticFactoryMethod()

	println(controlledInit)

	val uncontrolledInit = controlledInit.copy(onlyICanSetThis = "...or not")

	println(uncontrolledInit)
}

fun main() {
	`with equals, hashCode, and toString`()
	`with component and copy functions`()
	`with data classes in the standard library`()
	`with private data class constructor`()
}