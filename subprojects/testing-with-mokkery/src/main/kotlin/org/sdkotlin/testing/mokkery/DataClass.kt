package org.sdkotlin.testing.mokkery

@Suppress("unused") // Not mockable by Mokkery unless all-opened.
data class DataClass(
	override val v: ValueClassSuperType,
) : DataClassSuperType
