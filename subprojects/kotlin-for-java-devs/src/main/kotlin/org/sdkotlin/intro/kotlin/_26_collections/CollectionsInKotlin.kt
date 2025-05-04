package org.sdkotlin.intro.kotlin._26_collections

import java.util.LinkedList
import java.util.TreeMap
import java.util.TreeSet

fun main() {

	// As with arrays, collections in Kotlin are created with factory functions.

	val listOfInts: List<Int> = listOf(1, 2, 3)

	val otherList: List<Int> = List(3) { index -> index + 1 }

	println(otherList)

	val setOfInts = setOf(1, 2, 3)

	// The map factory function takes `Pair`s, which can be constructed with
	// the handy infix function `to`.

	val mapOfIntsToStrings = mapOf(1 to "1", 2 to "2", 3 to "3")

	// Collections support the index operator as with arrays.

	println("listOfInts.get(0): ${listOfInts.get(0)}")
	println("listOfInts[0]: ${listOfInts[0]}")

	// Except collections are immutable by default in Kotlin.

	//listOfInts.set(0, 100) // Does not compile.
	//listOfInts[0] = 100

	// There are factory functions for mutable collections.

	val mutableListOfInts: MutableList<Int> = mutableListOf(1, 2, 3)

	mutableListOfInts.set(0, 100)
	mutableListOfInts[0] = 100

	// You can also get a mutable copy of an immutable collection.

	val mutableCopiedListOfInts = listOfInts.toMutableList()
	val secondListOfInts = mutableCopiedListOfInts.toList()

	// For lists there is a constructor that takes a value factory similar to
	// that for Array.

	val listOfStrings = List(10) { index -> index.toString() }

	// You may wonder how List, being an interface, could have a constructor.
	// In a bit of DSL trickery, it's really just a top-level function in the
	// implicitly imported `kotlin.collections` package with a capitalized name.

	// There is a mutable equivalent.

	val mutableListOfStrings = MutableList(10) { index -> index.toString() }

	// If there is a concern with the short-lived `Pair` creation required for
	// `mapOf(...)`, we can create a mutable map, initialize the keys and
	// values directly, and then get an immutable reference to it.

	val fastMap = mutableMapOf<Int, String>().also {
		it[1] = "1"
		it[2] = "2"
		it[3] = "3"
	}.toMap()

	// There are factory functions for common collection implementations.

	// The `to[Type]()` methods can be used to get the generic immutable
	// references for them.

	val arrayListOfInts = arrayListOf(1, 2, 3).toList()

	val hashSetOfInts = hashSetOf(1, 2, 3).toSet()

	val hashMapOfIntsToStrings = hashMapOf(1 to "1", 2 to "2", 3 to "3").toMap()

	// If a factory function doesn't exist in the standard library for the
	// collection implementation you need, it's straightforward to add one.

	fun <T> linkedListOf(vararg items: T) = LinkedList<T>().apply {
		addAll(items)
	}

	fun <T> treeSetOf(vararg items: T) = TreeSet<T>().apply {
		addAll(items)
	}

	fun <K, V> treeMapOf(vararg items: Pair<K, V>) = TreeMap<K, V>().apply {
		putAll(items)
	}

	// Iterating collections is as with arrays, and there is a rich set of APIs
	// for accessing, mutating, ordering, searching, transforming, filtering,
	// grouping, aggregating, and stream processing them. There are also
	// collection type specific APIs like `union`, `intersect`, and `subtract`
	// for sets.

	// See the Kotlin reference for a thorough overview:
	// https://kotlinlang.org/docs/reference/collections-overview.html
}
