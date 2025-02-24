package org.sdkotlin.kotlin.reflect

sealed class SealedClass
class SubClass : SealedClass()

fun main() {
	println(SealedClass::class.sealedSubclasses)
}
