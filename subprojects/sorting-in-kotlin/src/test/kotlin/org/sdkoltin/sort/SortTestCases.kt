package org.sdkoltin.sort

internal object SortTestCases {

	val IN_PLACE_SORT_TEST_CASES: List<MutableList<Int>> = listOf(
		mutableListOf(),
		mutableListOf(1),
		mutableListOf(1, 2),
		mutableListOf(2, 1),
		mutableListOf(1, 2, 3),
		mutableListOf(2, 1, 3),
		mutableListOf(1, 3, 2),
		mutableListOf(3, 1, 2),
		mutableListOf(3, 2, 1),
	)
}
