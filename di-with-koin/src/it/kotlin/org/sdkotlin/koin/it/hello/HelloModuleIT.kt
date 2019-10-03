package org.sdkotlin.koin.it.hello

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import org.sdkotlin.koin.hello.HelloController
import org.sdkotlin.koin.hello.RandomGreetingService
import org.sdkotlin.koin.hello.helloModule

internal class HelloModuleIT : KoinTest {

	private val helloController: HelloController by inject()

	@BeforeEach
	fun beforeEach() {
		startKoin {
			modules(helloModule)
		}
	}

	@AfterEach
	fun afterEach() {
		stopKoin()
	}

	@Test
	fun `Integration test the hello module`() {

		val response = helloController.sayHello()

		assertThat(response).isIn(RandomGreetingService.GREETINGS)
	}
}
