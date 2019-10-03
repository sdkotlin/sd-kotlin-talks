package org.sdkotlin.koin.hello

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class EnglishGreetingServiceTest {

	@Test
	fun `should return an English greeting`() {

		val greetingService: GreetingService = EnglishGreetingService()

		val response = greetingService.getGreeting()

		assertThat(response).isIn(EnglishGreetingService.GREETING)
	}
}
