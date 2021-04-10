package org.sdkotlin.tdd.sorting

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DynamicTest.dynamicTest
import org.junit.jupiter.api.TestFactory

class JUnit5DynamicTestSortTest {

	@TestFactory
	fun `test sort`() = listOf(
		emptyArray<Int>() to emptyArray(),
		arrayOf(1) to arrayOf(1),
		arrayOf(1, 2) to arrayOf(1, 2),
		arrayOf(2, 1) to arrayOf(1, 2),
		arrayOf(1, 2, 3) to arrayOf(1, 2, 3),
		arrayOf(2, 1, 3) to arrayOf(1, 2, 3),
		arrayOf(1, 3, 2) to arrayOf(1, 2, 3),
		arrayOf(3, 2, 1) to arrayOf(1, 2, 3)
	).map { (unsortedArray, expectedArray) ->
		// Must run tests with IntelliJ instead of Gradle (Preferences >
		// Build, Execution, Deployment > Build Tools > Gradle >
		// Run tests using: IntelliJ IDEA) to see test names in IntelliJ per
		// https://github.com/gradle/gradle/issues/5975
		dynamicTest(
			"sort(${unsortedArray.contentToString()}) is ${expectedArray.contentToString()}"
		) {
			val sortedArray = sort(unsortedArray)
			Assertions.assertThat(sortedArray).isEqualTo(expectedArray).isNotSameAs(unsortedArray)
		}
	}
}
