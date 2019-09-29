package org.sdkotlin.tdd

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class JUnit5SortTest {

	@Test
	fun `test empty array`() {
		val array = emptyArray<Int>()
		val sortedArray = sort(array)
		assertThat(sortedArray).isEqualTo(array)
	}

	@Test
	fun `test single element array`() {
		val array = arrayOf(1)
		val sortedArray = sort(array)
		assertThat(sortedArray).isEqualTo(array)
	}

	@Test
	fun `test two element sorted array`() {
		val array = arrayOf(1, 2)
		val sortedArray = sort(array)
		assertThat(sortedArray).isEqualTo(array)
	}

	@Test
	fun `test two element unsorted array`() {
		val array = arrayOf(2, 1)
		val sortedArray = sort(array)
		assertThat(sortedArray).isEqualTo(arrayOf(1, 2))
	}

	@Test
	fun `test three element sorted array`() {
		val array = arrayOf(1, 2, 3)
		val sortedArray = sort(array)
		assertThat(sortedArray).isEqualTo(arrayOf(1, 2, 3))
	}

	@Test
	fun `test three element left hand unsorted array`() {
		val array = arrayOf(2, 1, 3)
		val sortedArray = sort(array)
		assertThat(sortedArray).isEqualTo(arrayOf(1, 2, 3))
	}

	@Test
	fun `test three element right hand unsorted array`() {
		val array = arrayOf(1, 3, 2)
		val sortedArray = sort(array)
		assertThat(sortedArray).isEqualTo(arrayOf(1, 2, 3))
	}

	@Test
	fun `test three element reversed array`() {
		val array = arrayOf(3, 2, 1)
		val sortedArray = sort(array)
		assertThat(sortedArray).isEqualTo(arrayOf(1, 2, 3))
	}
}

