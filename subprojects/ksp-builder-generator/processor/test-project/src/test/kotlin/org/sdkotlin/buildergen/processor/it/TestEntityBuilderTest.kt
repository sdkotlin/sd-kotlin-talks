package org.sdkotlin.buildergen.processor.it

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.sdkotlin.buildergen.api.builder.Builder

internal class TestEntityBuilderTest {

	companion object {
		const val TEST_INT = 1
		const val TEST_STRING = "Testing"
	}

	@Test
	fun `test generated builder`() {

		val testEntityBuilder = TestEntityBuilder()

		testEntityBuilder.testInt = TEST_INT
		testEntityBuilder.testNullableInt = null
		testEntityBuilder.testString = TEST_STRING
		testEntityBuilder.testNullableString = null

		assertThat(testEntityBuilder).isInstanceOf(Builder::class.java)

		val testEntity: TestEntity = testEntityBuilder.build()

		assertThat(testEntity.testInt).isEqualTo(TEST_INT)
		assertThat(testEntity.testNullableInt).isNull()
		assertThat(testEntity.testString).isEqualTo(TEST_STRING)
		assertThat(testEntity.testNullableString).isNull()
	}
}
