package org.sdkotlin.testing.mokkery

import dev.mokkery.answering.returns
import dev.mokkery.every
import dev.mokkery.mock
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

interface ValueClassSuperType

@JvmInline
value class ValueClass(val s: String) : ValueClassSuperType

interface DataClassSuperType {
	val v: ValueClassSuperType
}

@Suppress("unused") // Not mockable by Mokkery unless all-opened.
data class DataClass(
	override val v: ValueClassSuperType,
) : DataClassSuperType

class MokkeryValueClassParameterTest {

	@Test
	fun `test value class parameter`() {

		val testValue = ValueClass("testing")

		val mock = mock<DataClassSuperType> {
			every { v } returns testValue
		}

		assertThat(mock.v).isEqualTo(testValue)
	}
}
