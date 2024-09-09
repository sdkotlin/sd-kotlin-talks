package org.sdkotlin.intro.kotlin._08_3_extensionfunctions.otherpackage

import org.sdkotlin.intro.kotlin._08_3_extensionfunctions.shuffled

fun main() {

	// Extension functions aren't really added to the receiver classes. They
	// must be imported to be used in a scope other than where they are defined.

	println("Hello".shuffled())
}
