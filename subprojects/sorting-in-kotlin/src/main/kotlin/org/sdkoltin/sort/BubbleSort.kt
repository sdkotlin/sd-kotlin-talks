package org.sdkoltin.sort

/**
 * Sorts the list in-place via a stable, iterative implementation of the Bubble
 * Sort algorithm.
 */
fun <E : Comparable<E>> MutableList<E>.bubbleSort() {

	if (size <= 1) return

	for (i in 0 until size - 1) {
		for (j in 0 until size - i - 1) {
			if (this[j] > this[j + 1]) this.swap(j, j + 1)
		}
	}
}
