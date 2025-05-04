package org.sdkotlin.buildergen.api.annotations

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class GeneratedBuilderTest {

	@GeneratedBuilder
	data class TestEntity(
		val b: Boolean,
	)

	@Test
	fun `verify GeneratedBuilder target`() {

		assertThat(TestEntity::class.java)
			.hasAnnotation(GeneratedBuilder::class.java)
	}
}
