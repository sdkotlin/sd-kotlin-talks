package org.sdkotlin.tdd.sorting

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class JUnit5LoopingSortTest {

	@Test
	fun `test sort`() {

		// Assemble
		val testCases = listOf(
			emptyArray<Int>() to emptyArray(),
			arrayOf(1) to arrayOf(1),
			arrayOf(1, 2) to arrayOf(1, 2),
			arrayOf(2, 1) to arrayOf(1, 2),
			arrayOf(1, 2, 3) to arrayOf(1, 2, 3),
			arrayOf(2, 1, 3) to arrayOf(1, 2, 3),
			arrayOf(1, 3, 2) to arrayOf(1, 2, 3),
			arrayOf(3, 2, 1) to arrayOf(1, 2, 3),
		)

		// Avoid: Fails fast (consider `@TestFactory` instead).
		for ((unsortedArray, sortedArray) in testCases) {

			// Act
			val sortResult = sort(unsortedArray)

			// Assert
			assertThat(sortResult)
				.isEqualTo(sortedArray)
				.isNotSameAs(unsortedArray)
		}
	}
}
