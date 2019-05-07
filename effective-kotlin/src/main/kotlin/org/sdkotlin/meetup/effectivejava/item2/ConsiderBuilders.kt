package org.sdkotlin.meetup.effectivejava.item2

/* Effective Java
Item 2: Consider a builder when faced with many constructor parameters
 */

// Kotlin supports named and default parameters, which make the telescoping
// constructor and method patterns, and in many cases the builder pattern,
// obsolete.

data class NutritionFacts(
		val servingSize: Int,
		val servings: Int,
		val calories: Int = 0,
		val fat: Int = 0,
		val sodium: Int = 0,
		val carbohydrate: Int = 0
)

fun main(args: Array<String>) {

	val sodaNutritionFacts =
			NutritionFacts(servingSize = 12, servings = 2, calories = 180)

	val pizzaNutritionFacts =
			NutritionFacts(servingSize = 1, servings = 12, calories = 250,
					fat = 12,
					carbohydrate = 24)

	println("Soda facts: $sodaNutritionFacts")
	println("Pizza facts: $pizzaNutritionFacts")
}
