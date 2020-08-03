package org.sdkotlin.intro.kotlin._26_nullsafety

fun main() {

	var name = "Ezra"
	// name = null // Does not compile

	val nullableName: String? = null

	println("Non-nullable name length: ${name.length}")

	// println(nullableName.length) // Does not compile
	println("Safe nullable name length: ${nullableName?.length}")

	if (nullableName != null) {
		println("Smart casted nullable name length: ${nullableName.length}")
	}

	val possibleNoNames = listOf("Ezra", null, "Luke")

	for (possibleName in possibleNoNames) {
		possibleName?.let { println("Let's only print non-null names: ${it}") }
	}

	for (possibleName in possibleNoNames) {
		val defaultedName = possibleName ?: "Elvis"
		println("""Or we could use the "Elvis" operator to default null names: $defaultedName""")
	}

	try {
		val requiredName =
				nullableName ?: throw IllegalArgumentException(
						"Or we could throw a better exception for missing arguments")
	} catch (e: IllegalArgumentException) {
		println(e.message)
	}

	for (definiteName in possibleNoNames.filterNotNull()) {
		println("Or we could use filtering and smart casting: ${definiteName.length}")
	}

	try {
		nullableName!!.length
	} catch (e: NullPointerException) {
		println("Or we could really just love NullPointerExceptions!!")
	}
}
