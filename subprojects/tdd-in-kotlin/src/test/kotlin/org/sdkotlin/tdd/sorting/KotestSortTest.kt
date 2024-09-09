package org.sdkotlin.tdd.sorting

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.headers
import io.kotest.data.row
import io.kotest.data.table
import io.kotest.matchers.shouldBe

internal class KotestSortTest : StringSpec({

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
