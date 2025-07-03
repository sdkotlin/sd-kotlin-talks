package org.sdkotlin.equalsverifier.delegation

import nl.jqno.equalsverifier.EqualsVerifier
import nl.jqno.equalsverifier.Mode
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class FooBarImplTest {

	@Test
	fun `test equals, hashCode, and toString`() {

		EqualsVerifier.forClass(FooBarImpl::class.java)
			.withIgnoredFields(Foo::foo.name)
			// Mockito skip or prefab values required per
			// https://github.com/jqno/equalsverifier/issues/1083.
			.set(Mode.skipMockito())
			//.withPrefabValues(BarImpl::class.java, BarImpl(1), BarImpl(2))
			.verify()
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
