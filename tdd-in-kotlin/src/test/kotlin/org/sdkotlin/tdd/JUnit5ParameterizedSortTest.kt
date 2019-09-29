package org.sdkotlin.tdd

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class JUnit5ParameterizedSortTest {

	companion object {
		@JvmStatic
		fun arrays() = listOf(
			arguments(emptyArray<Int>(), emptyArray<Int>()),
			arguments(arrayOf(1), arrayOf(1)),
			arguments(arrayOf(1, 2), arrayOf(1, 2)),
			arguments(arrayOf(2, 1), arrayOf(1, 2)),
			arguments(arrayOf(1, 2, 3), arrayOf(1, 2, 3)),
			arguments(arrayOf(2, 1, 3), arrayOf(1, 2, 3)),
			arguments(arrayOf(1, 3, 2), arrayOf(1, 2, 3)),
			arguments(arrayOf(3, 2, 1), arrayOf(1, 2, 3))
		)
	}

	@ParameterizedTest(name = "{arguments}")
	@MethodSource("arrays")
	fun `test sort`(actual: Array<Int>, expected: Array<Int>) {
		val sortedArray = sort(actual)
		assertThat(sortedArray).isEqualTo(expected).isNotSameAs(actual)
	}
}
