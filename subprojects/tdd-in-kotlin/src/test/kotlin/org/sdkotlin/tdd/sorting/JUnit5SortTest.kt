package org.sdkotlin.tdd.sorting

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class JUnit5SortTest {

	@Test
	fun `test empty array`() {

		// Assemble
		val unsortedArray = emptyArray<Int>()
		val sortedArray = emptyArray<Int>()

		// Act
		val sortResult = sort(unsortedArray)

		// Assert
		assertThat(sortResult)
			.isEqualTo(sortedArray)
			.isNotSameAs(unsortedArray)
	}

	@Test
	fun `test single element array`() {

		val unsortedArray = arrayOf(1)
		val sortedArray = arrayOf(1)

		val sortResult = sort(unsortedArray)

		assertThat(sortResult).isEqualTo(sortedArray)
	}

	@Test
	fun `test two element sorted array`() {

		val unsortedArray = arrayOf(1, 2)
		val sortedArray = arrayOf(1, 2)

		val sortResult = sort(unsortedArray)

		assertThat(sortResult).isEqualTo(sortedArray)
	}

	@Test
	fun `test two element unsorted array`() {

		val unsortedArray = arrayOf(2, 1)
		val sortedArray = arrayOf(1, 2)

		val sortResult = sort(unsortedArray)

		assertThat(sortResult).isEqualTo(sortedArray)
	}

	@Test
	fun `test three element sorted array`() {

		val unsortedArray = arrayOf(1, 2, 3)
		val sortedArray = arrayOf(1, 2, 3)

		val sortResult = sort(unsortedArray)

		assertThat(sortResult).isEqualTo(sortedArray)
	}

	@Test
	fun `test three element left hand unsorted array`() {

		val unsortedArray = arrayOf(2, 1, 3)
		val sortedArray = arrayOf(1, 2, 3)

		val sortResult = sort(unsortedArray)

		assertThat(sortResult).isEqualTo(sortedArray)
	}

	@Test
	fun `test three element right hand unsorted array`() {

		val unsortedArray = arrayOf(1, 3, 2)
		val sortedArray = arrayOf(1, 2, 3)

		val sortResult = sort(unsortedArray)

		assertThat(sortResult).isEqualTo(sortedArray)
	}

	@Test
	fun `test three element reversed array`() {

		val unsortedArray = arrayOf(3, 2, 1)
		val sortedArray = arrayOf(1, 2, 3)

		val sortResult = sort(unsortedArray)

		assertThat(sortResult).isEqualTo(sortedArray)
	}
}
