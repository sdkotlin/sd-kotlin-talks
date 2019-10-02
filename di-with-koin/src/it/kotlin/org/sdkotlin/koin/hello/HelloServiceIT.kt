package org.sdkotlin.koin.hello

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.koin.core.context.startKoin
import org.koin.test.KoinTest
import org.koin.test.inject

class HelloServiceIT : KoinTest {

	private val helloService : HelloService by inject()

	@Test
	fun `Integration test the HelloService's module`() {
		startKoin {
			modules(helloServiceModule)
		}

		val response = helloService.sayHello()

		assertThat(response).isEqualTo(HelloService.GREETING)
	}
}
