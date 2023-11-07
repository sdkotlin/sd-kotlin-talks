package org.sdkotlin.tdd.sorting

import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec
import io.kotlintest.tables.forAll
import io.kotlintest.tables.headers
import io.kotlintest.tables.row
import io.kotlintest.tables.table

internal class KotlinTestSortTest : StringSpec({

	"should return elements in order for" {
		val unsortedArrays = table(
			headers("unsortedArray", "expectedArray"),
			row(emptyArray(), emptyArray()),
			row(arrayOf(1), arrayOf(1)),
			row(arrayOf(1, 2), arrayOf(1, 2)),
			row(arrayOf(2, 1), arrayOf(1, 2)),
			row(arrayOf(1, 2, 3), arrayOf(1, 2, 3)),
			row(arrayOf(2, 1, 3), arrayOf(1, 2, 3)),
			row(arrayOf(1, 3, 2), arrayOf(1, 2, 3)),
			row(arrayOf(3, 2, 1), arrayOf(1, 2, 3))
		)
		forAll(unsortedArrays) { unsortedArray, sortedArray ->
			sort(unsortedArray) shouldBe sortedArray
		}
	}
})
