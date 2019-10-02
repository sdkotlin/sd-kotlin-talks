package org.sdkotlin.koin.hello

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class HelloServiceTest {

	@Test
	fun `HelloService should say hello`() {

		val helloService = HelloService()

		val response = helloService.sayHello()

		assertThat(response).isEqualTo(HelloService.GREETING)
	}
}
