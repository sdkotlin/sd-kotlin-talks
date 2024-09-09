package org.sdkotlin.koin.hello

class EnglishGreetingService : GreetingService {

	companion object {
		const val GREETING = "Hi, World!"
	}

	override fun getGreeting() = GREETING
}
