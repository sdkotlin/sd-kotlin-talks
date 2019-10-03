package org.sdkotlin.koin.it.hello.test

import org.sdkotlin.koin.hello.GreetingService

internal class TestGreetingService : GreetingService {

	companion object {
		const val TEST_GREETING = "Howdy!"
	}

	override fun getGreeting() = TEST_GREETING
}
