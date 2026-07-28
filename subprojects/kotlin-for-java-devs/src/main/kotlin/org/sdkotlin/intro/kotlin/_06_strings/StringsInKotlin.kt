package org.sdkotlin.intro.kotlin._06_strings

import org.sdkotlin.intro.java._06_strings.StringsInJava.STRING

fun main() {

	// Kotlin supports string templates.

	val yay = "yay"

	val template = "Kotlin supports String templates, $yay!\n\n"

	println(template)

	val otherTemplate = "You can use arbitrary expressions if you" +
		"add curly braces: ${1 + 1}"

	println(otherTemplate)

	val rawString = """
		You can use "raw" strings for more substantial bodies of text.
		val someString = "That gets you out of "
				+ "concatenation-wrapping, and lets you use literal whitespace "
				+ "instead of escape sequences such as \t, \n, etc."
		You can still use string templates in raw strings: $STRING
		You can still put arbitrary expressions in those string templates with
		curly braces. ${"\n".repeat(2)}

		You can trim leading indentation.
		""".trimIndent()

	val prefixedRawString = """
		> You can also trim to a prefix string,
		> as with email-style quotations.
		> The default prefix is "|" for `.trimMargin()`.
		> > Trimming is not eager.
		""".trimMargin("> ")

	println(template + rawString + prefixedRawString)

	// Raw strings have no escape sequences, so you used to get a literal
	// dollar sign by interpolating one. Java has no such problem: text blocks
	// don't interpolate, and the string templates preview was withdrawn.

	val oldDollarEscape = """
		BUDGET=${'$'}500
		""".trimIndent()

	println(oldDollarEscape)

	// As of Kotlin 2.2, you can prefix the literal with dollar signs to set
	// how many in a row start interpolation. With two, a lone dollar sign is
	// just text.

	val budget = 500

	val shellSnippet = $$"""
		BUDGET=$${budget}
		echo "Spent $SPENT of $BUDGET"
		""".trimIndent()

	println(shellSnippet)
}
