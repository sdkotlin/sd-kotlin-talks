package org.sdkotlin.tdd.sorting

fun <T : Comparable<T>> sort(array: Array<T>): Array<T> = array.copyOf().sortedArray()
