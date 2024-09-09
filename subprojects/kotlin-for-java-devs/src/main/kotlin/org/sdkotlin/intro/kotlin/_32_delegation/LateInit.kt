package org.sdkotlin.intro.kotlin._32_delegation

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class LateInit<T>(
	val createException: (s: String) -> Exception,
) : ReadWriteProperty<Any, T> {

	private object EMPTY

	private var value: Any? = EMPTY

	override fun getValue(
		thisRef: Any,
		property: KProperty<*>,
	): T {
		if (value == EMPTY) {
			throw createException("${property.name} isn't initialized")
		} else {
			@Suppress("UNCHECKED_CAST") // Must be EMPTY or a T.
			return value as T
		}
	}

	override fun setValue(
		thisRef: Any,
		property: KProperty<*>,
		value: T,
	) {
		if (this.value != EMPTY) {
			throw createException("${property.name} already initialized")
		}
		this.value = value
	}
}

inline fun <reified T> lateInit(
	noinline createException: (s: String) -> Exception =
		{ IllegalStateException(it) },
): ReadWriteProperty<Any, T> =
	LateInit(createException)
