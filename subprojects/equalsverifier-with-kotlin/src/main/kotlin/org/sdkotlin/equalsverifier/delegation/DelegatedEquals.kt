package org.sdkotlin.equalsverifier.delegation

interface Foo {
	val foo: Int
}

interface Bar {
	val bar: Int
}

data class BarImpl(override val bar: Int) : Bar

class FooBarImpl(barValue: Int) : Foo, Bar by BarImpl(barValue) {

	override val foo = -bar

	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (other !is FooBarImpl) return false
		return bar == other.bar
	}

	override fun hashCode(): Int = bar
	override fun toString(): String = "FooBarImpl(foo=$bar)"
}

interface Baz {
	val bar: Bar
}

data class BazImpl(override val bar: Bar) : Baz
