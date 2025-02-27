package org.sdkotlin.intro.kotlin._07_equality

fun main() {

	println(java.lang.String("foo") === java.lang.String("foo"))
	println(java.lang.String("foo") == java.lang.String("foo"))

	// Equivalent to "=="
	println("foo".equals("foo"))

	// TODO: Research and explain why this prints "true"...
	println("fo" + "o" === "foo")

	println("foo" == "foo")
}
