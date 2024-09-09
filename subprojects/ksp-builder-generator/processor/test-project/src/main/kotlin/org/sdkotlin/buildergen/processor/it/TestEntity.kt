package org.sdkotlin.buildergen.processor.it

import org.sdkotlin.buildergen.api.annotations.GeneratedBuilder

@GeneratedBuilder
data class TestEntity(
	val testInt: Int,
	val testNullableInt: Int?,
	val testString: String,
	val testNullableString: String?,
)
