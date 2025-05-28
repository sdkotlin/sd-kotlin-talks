package org.sdkotlin.equalsverifier.recusive

data class DataClassWithSealedTypeProperty(
	val foo: Foo,
)

sealed interface Foo {

	val value: Int

	companion object {
		fun get(value: Int): Foo =
			FooEnum.INSTANCES[value] ?: UnknownFooImpl(value)
	}
}

enum class FooEnum(
	private val delegate: Foo,
) : Foo by delegate {

	FOO_1(1),
	FOO_2(2),
	;

	constructor(
		value: Int,
	) : this(FooImpl(value))

	companion object {
		val INSTANCES: Map<Int, FooEnum> =
			entries.associateBy { enum -> enum.value }
	}
}

internal data class FooImpl(
	override val value: Int,
) : Foo

internal data class UnknownFooImpl(
	override val value: Int,
) : Foo
