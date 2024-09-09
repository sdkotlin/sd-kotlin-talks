package org.sdkoltin.sort

/**
 * In-place swaps the elements at the given indices.
 *
 * @param i the first index.
 * @param j the second index.
 * @throws IllegalStateException if the list size is less than 2.
 * @throws IllegalArgumentException if a given index is out of bounds, or if
 * the given indices are equal.
 */
fun <E> MutableList<E>.swap(i: Int, j: Int) {

	check(size >= 2) {
		"Can't swap elements for a list with size $size!"
	}

	require(i in 0 until size) {
		"Index i = $i is out of bounds for list with size $size!"
	}

	require(j in 0 until size) {
		"Index j = $j is out of bounds for list with size $size!"
	}

	require(i != j) {
		"Can't swap elements for the same i and j index $i"
	}

	val temp = this[j]
	this[j] = this[i]
	this[i] = temp
}
