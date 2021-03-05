package org.sdkotlin.tdd.sorting

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class JUnit5ParameterizedSortTest {

	companion object {
		@JvmStatic
		fun arrays() = listOf(
			Arguments.of(emptyArray<Int>(), emptyArray<Int>()),
			arguments(arrayOf(1), arrayOf(1)),
			arguments(arrayOf(1, 2), arrayOf(1, 2)),
			arguments(arrayOf(2, 1), arrayOf(1, 2)),
			arguments(arrayOf(1, 2, 3), arrayOf(1, 2, 3)),
			arguments(arrayOf(2, 1, 3), arrayOf(1, 2, 3)),
			arguments(arrayOf(1, 3, 2), arrayOf(1, 2, 3)),
			arguments(arrayOf(3, 2, 1), arrayOf(1, 2, 3))
		)
	}

	@ParameterizedTest(name = "sort({0}) is {1}")
	@MethodSource("arrays")
	fun `test sort`(unsortedArray: Array<Int>, sortedArray: Array<Int>) {
		assertThat(sort(unsortedArray)).isEqualTo(sortedArray).isNotSameAs(unsortedArray)
	}
}
