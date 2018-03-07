package org.sdkotlin.meetup

fun main(args: Array<String>) {

	println(java.lang.String("foo") === java.lang.String("foo"))
	println(java.lang.String("foo") == java.lang.String("foo"))

	println("fo" + "o" === "foo")
	println("foo" == "foo")
}
