package org.sdkotlin.koin.hello

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class RandomGreetingServiceTest {

	@Test
	fun `should return an random greeting`() {

		val greetingService: GreetingService = RandomGreetingService()

		val response = greetingService.getGreeting()

		assertThat(response).isIn(RandomGreetingService.GREETINGS)
	}
}
