package org.sdkotlin.equalsverifier.delegation

interface LazyEquals {
	val foo: Int
	val bar: String
}

class LazyEqualsImpl(foo: Int, bar: String) : LazyEquals {

	override val foo: Int by lazy { foo }
	override val bar: String by lazy { bar }

	override fun equals(other: Any?) =
		other is LazyEqualsImpl && other.foo == foo && other.bar == bar

	override fun hashCode() = foo.hashCode() + 31 * bar.hashCode()
	override fun toString() = "LazyEqualsImpl(foo=$foo, bar=$bar)"
}
