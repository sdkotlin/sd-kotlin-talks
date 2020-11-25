// TODO: https://youtrack.jetbrains.com/issue/KT-34102
@file:Suppress("invisible_reference", "invisible_member")
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
import org.sdkotlin.koin.hello.helloModule
import org.sdkotlin.koin.it.hello.test.TestGreetingService
import org.sdkotlin.koin.it.hello.test.testHelloModule

internal class HelloControllerIT : KoinTest {

	private val helloController: HelloController by inject()

	@BeforeEach
	fun beforeEach() {
		startKoin {
			modules(listOf(helloModule, testHelloModule))
		}
	}

	@AfterEach
	fun afterEach() {
		stopKoin()
	}

	@Test
	fun `Integration test the hello controller`() {

		val response = helloController.sayHello()

		assertThat(response).isEqualTo(TestGreetingService.TEST_GREETING)
	}
}
