package org.sdkotlin.tdd.sorting

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DynamicTest.dynamicTest
import org.junit.jupiter.api.TestFactory

internal class JUnit5DynamicTestSortTest {

	@TestFactory
	fun `test sort`() =
		// Assemble
		listOf(
			emptyArray<Int>() to emptyArray(),
			arrayOf(1) to arrayOf(1),
			arrayOf(1, 2) to arrayOf(1, 2),
			arrayOf(2, 1) to arrayOf(1, 2),
			arrayOf(1, 2, 3) to arrayOf(1, 2, 3),
			arrayOf(2, 1, 3) to arrayOf(1, 2, 3),
			arrayOf(1, 3, 2) to arrayOf(1, 2, 3),
			arrayOf(3, 2, 1) to arrayOf(1, 2, 3),
		).map { (unsortedArray, expectedArray) ->
			dynamicTest(
				"sort(${unsortedArray.contentToString()}) is ${expectedArray.contentToString()}"
			) {
				// Act
				val sortedArray = sort(unsortedArray)

				// Assert
				assertThat(sortedArray)
					.isEqualTo(expectedArray)
					.isNotSameAs(unsortedArray)
			}
		}
}
