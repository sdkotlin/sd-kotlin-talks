package org.sdkotlin.tdd.mockk

import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

interface ValueClassSuperType

@JvmInline
value class ValueClass(val s: String) : ValueClassSuperType

data class TestDataClass(val v: ValueClassSuperType)

class MockKValueClassParameterTest {

	@Test
	@Disabled("https://github.com/mockk/mockk/issues/1342")
	fun `test value class parameter`() {

		val testValue = ValueClass("testing")

		val mock = mockk<TestDataClass> {
			every { v } returns testValue
		}

		assertThat(mock.v).isEqualTo(testValue)
	}
}
