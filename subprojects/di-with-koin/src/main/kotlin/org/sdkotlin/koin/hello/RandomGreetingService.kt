package org.sdkotlin.koin.hello

import kotlin.random.Random

class RandomGreetingService : GreetingService {

	companion object {
		val GREETINGS = listOf(
			"Hello, World!",
			"¡Hola Mundo!",
			"Salamu, Dunia!",
			"Aloha nui kāua!"
		)
	}

	override fun getGreeting(): String {

		return GREETINGS[Random.nextInt(GREETINGS.size)]
	}
}
