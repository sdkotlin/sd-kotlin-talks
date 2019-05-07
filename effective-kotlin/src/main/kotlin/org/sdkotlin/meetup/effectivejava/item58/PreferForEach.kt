package org.sdkotlin.meetup.effectivejava.item58

/* Effective Java
Item 58: Prefer for-each loops to traditional for loops
 */

// Kotlin only has for-each loops!

fun main(args: Array<String>) {

	val shapes = arrayOf("Circle", "Triangle", "Rectangle")

	/* Doesn't compile...
	for (int i; i < shapes.size; i++) {
		println("The shape is ${shapes[i]}")
	}
	*/

	for (i: Int in shapes.indices) {
		println("The shape is ${shapes[i]}")
	}

	for (shape: String in shapes) {
		println("Again the shape is $shape")
	}
}
