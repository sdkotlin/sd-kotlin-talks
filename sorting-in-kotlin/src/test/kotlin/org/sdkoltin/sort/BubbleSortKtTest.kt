package org.sdkoltin.sort

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DynamicTest.dynamicTest
import org.junit.jupiter.api.TestFactory
import org.sdkoltin.sort.SortTestCases.IN_PLACE_SORT_TEST_CASES

internal class BubbleSortKtTest {

	@TestFactory
	fun `test bubble sort`() =
		IN_PLACE_SORT_TEST_CASES.map { mutableList ->

			dynamicTest("test bubble sort for $mutableList") {

				mutableList.bubbleSort()

				assertThat(mutableList).isSorted
			}
		}
}
