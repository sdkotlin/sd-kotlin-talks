package org.sdkotlin.buildergen.processor.it

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.sdkotlin.buildergen.api.builder.Builder

internal class TestEntityBuilderTest {

	@Test
	fun `test generated builder`() {

		val testEntityBuilder: Builder<TestEntity> = TestEntityBuilder()

		assertThat(testEntityBuilder).isInstanceOf(Builder::class.java)
	}
}
