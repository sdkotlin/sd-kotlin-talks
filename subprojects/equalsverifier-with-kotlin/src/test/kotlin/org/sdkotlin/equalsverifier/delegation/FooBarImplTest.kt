package org.sdkotlin.equalsverifier.delegation

import nl.jqno.equalsverifier.EqualsVerifier
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

class FooBarImplTest {

	@Test
	@Disabled("https://github.com/jqno/equalsverifier/issues/1083")
	fun `test equals, hashCode, and toString`() {
		EqualsVerifier.forClass(FooBarImpl::class.java).verify()
	}

	@Test
	fun `test equals for equal`() {
		val actual = FooBarImpl(1)
		val expected = FooBarImpl(1)
		assertThat(actual).isEqualTo(expected)
	}

	@Test
	fun `test equals for not equal`() {
		val actual = FooBarImpl(1)
		val expected = FooBarImpl(2)
		assertThat(actual).isNotEqualTo(expected)
	}
}
