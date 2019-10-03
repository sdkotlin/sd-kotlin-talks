package org.sdkotlin.koin.hello

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class SimpleHelloControllerTest {

	@Test
	fun `test say hello`() {

		val expectedGreeting = "Hi there!"

		val helloService = mock<GreetingService> {
			on { getGreeting() } doReturn expectedGreeting
		}

		val helloController = SimpleHelloController(helloService)

		val greeting = helloController.sayHello()

		assertThat(greeting).isEqualTo(expectedGreeting)
	}
}
