package org.sdkotlin.koin.hello

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

internal class SimpleHelloControllerTest {

	@Test
	fun `should say hello`() {

		val expectedGreeting = "Hi!"

		val helloService = mock<GreetingService> {
			on { getGreeting() } doReturn expectedGreeting
		}

		val helloController = SimpleHelloController(helloService)

		val greeting = helloController.sayHello()

		assertThat(greeting).isEqualTo(expectedGreeting)
	}
}
