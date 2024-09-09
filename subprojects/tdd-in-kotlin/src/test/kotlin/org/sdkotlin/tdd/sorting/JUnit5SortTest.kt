package org.sdkotlin.tdd.sorting

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class JUnit5SortTest {

	@Test
	fun `test empty array`() {
		val unsortedArray = emptyArray<Int>()
		val sortedArray = emptyArray<Int>()
		assertThat(sort(unsortedArray)).isEqualTo(sortedArray)
	}

	@Test
	fun `test single element array`() {
		val unsortedArray = arrayOf(1)
		val sortedArray = arrayOf(1)
		assertThat(sort(unsortedArray)).isEqualTo(sortedArray)
	}

	@Test
	fun `test two element sorted array`() {
		val unsortedArray = arrayOf(1, 2)
		val sortedArray = arrayOf(1, 2)
		assertThat(sort(unsortedArray)).isEqualTo(sortedArray)
	}

	@Test
	fun `test two element unsorted array`() {
		val unsortedArray = arrayOf(2, 1)
		val sortedArray = arrayOf(1, 2)
		assertThat(sort(unsortedArray)).isEqualTo(sortedArray)
	}

	@Test
	fun `test three element sorted array`() {
		val unsortedArray = arrayOf(1, 2, 3)
		val sortedArray = arrayOf(1, 2, 3)
		assertThat(sort(unsortedArray)).isEqualTo(sortedArray)
	}

	@Test
	fun `test three element left hand unsorted array`() {
		val unsortedArray = arrayOf(2, 1, 3)
		val sortedArray = arrayOf(1, 2, 3)
		assertThat(sort(unsortedArray)).isEqualTo(sortedArray)
	}

	@Test
	fun `test three element right hand unsorted array`() {
		val unsortedArray = arrayOf(1, 3, 2)
		val sortedArray = arrayOf(1, 2, 3)
		assertThat(sort(unsortedArray)).isEqualTo(sortedArray)
	}

	@Test
	fun `test three element reversed array`() {
		val unsortedArray = arrayOf(3, 2, 1)
		val sortedArray = arrayOf(1, 2, 3)
		assertThat(sort(unsortedArray)).isEqualTo(sortedArray)
	}
}
