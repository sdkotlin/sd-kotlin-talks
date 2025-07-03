package org.sdkotlin.equalsverifier.delegation

interface LazyEquals {
	val foo: Int
}

class LazyEqualsImpl(foo: Int) : LazyEquals {

	override val foo: Int by lazy { foo }

	override fun equals(other: Any?) =
		other is LazyEqualsImpl && other.foo == foo

	override fun hashCode() = foo.hashCode()
	override fun toString() = "LazyEqualsImpl(foo=$foo)"
}
