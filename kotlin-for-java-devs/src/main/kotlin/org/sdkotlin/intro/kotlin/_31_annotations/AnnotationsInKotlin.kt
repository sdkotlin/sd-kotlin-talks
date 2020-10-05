package org.sdkotlin.intro.kotlin._31_annotations

import kotlin.DeprecationLevel.WARNING
import kotlin.annotation.AnnotationRetention.SOURCE
import kotlin.annotation.AnnotationTarget.FUNCTION

// We can use use annotations in Kotlin just as we do in Java.

@Suppress("unused")
fun notUsed() = "YAGNI?"

// Annotation arguments must be compile-time constants.

const val unused = "unused"

@Suppress(unused)
fun alsoUnused() = "Dead code?"

// Kotlin has an annotation for deprecation that is a bit more featured than
// Java's.

fun newSchool(why: String) = println(why)

@Deprecated(
		message = "Seek alternate route.",
		replaceWith = ReplaceWith("newSchool(why)"),
		level = WARNING // or ERROR, HIDDEN
)
fun oldSchool(why: String) = println(why)

fun `with deprecation`() {

	val why = "You're my boy, Blue!"

	// IntelliJ offers a quick fix thanks to the `ReplaceWith`.

	oldSchool(why)
}

// New annotations are declared with `annotation class` instead of Java's
// `@interface`.

annotation class Great

// Annotation parameters are declared as primary constructor properties. They
// must be `val`.

annotation class SuperGreat(val great: String)

// As in Java, the allowed parameter types are limited:
//   - types that correspond to Java primitive types (Int, Long, etc.)
//   - strings
//   - classes (Foo::class)
//   - enums
//   - other annotations
//   - arrays of the types listed above

// Annotation parameters can have default values, just like any primary
// constructor parameter.

annotation class InherentlyGreat(val great: String = "Great!")

// Annotation classes can even take other annotations classes as parameters.

annotation class MetaGreat(val great: InherentlyGreat)

// When supplied as arguments, annotation classes are treated as regular
// classes (i.e., no qualifying `@` is used).

@MetaGreat(InherentlyGreat())
fun greatness() = ""

// As with Java, if an annotation has a parameter named "value" the name can be
// omitted when the parameter is used.

annotation class Valuable(val value: String)

@Valuable("This much")
fun valuation() = ""

// Annotations can have a `vararg` parameter.

annotation class VarargAnnotation(vararg val value: String)

@VarargAnnotation("One", "Two")
fun varagAnnotations() = ""

// Kotlin supports `[]` array literals for annotations.

annotation class ArrayAnnotation(val value: Array<String>)

@ArrayAnnotation(["One", "Two"])
fun arrayAnnotations() = ""

// As with Java, Kotlin has meta-annotations for controlling where and how
// annotations are used.

@Target(FUNCTION) // See KDoc for defaults
@Retention(SOURCE) // or BINARY, RUNTIME (default)
@Repeatable // `SOURCE` retention only
@MustBeDocumented // Include in the KDoc for the element
annotation class RedundantAnnotation

@RedundantAnnotation
@RedundantAnnotation
fun test() = ""

fun main() {
	`with deprecation`()
}
