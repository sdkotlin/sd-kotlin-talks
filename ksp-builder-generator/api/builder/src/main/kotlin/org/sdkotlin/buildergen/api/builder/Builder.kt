package org.sdkotlin.buildergen.api.builder

fun interface Builder<T> {

	fun build(): T
}
