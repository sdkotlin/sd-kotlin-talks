package org.sdkotlin.tdd

fun <T : Comparable<T>> sort(array: Array<T>): Array<T> = array.copyOf().sortedArray()
