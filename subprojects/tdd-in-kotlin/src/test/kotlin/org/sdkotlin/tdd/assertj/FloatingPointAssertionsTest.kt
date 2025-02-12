package org.sdkotlin.tdd.assertj

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.within
import org.assertj.core.api.Assertions.withinPercentage
import org.junit.jupiter.api.Test

private const val OFFSET = 0.00001

internal class FloatingPointAssertionsTest {

	@Test
	fun `test double assertion`() {

		val expected = 0.3
		val actual: Double = 0.1 + 0.2

		assertThat(actual)
			.isEqualTo(expected, within(OFFSET))
			.isCloseTo(expected, withinPercentage(1.0))
	}
}
