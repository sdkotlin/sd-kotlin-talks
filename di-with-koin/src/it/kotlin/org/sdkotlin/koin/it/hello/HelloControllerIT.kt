// TODO: Remove when https://youtrack.jetbrains.com/issue/KTIJ-23114 is fixed.
@file:Suppress("invisible_reference", "invisible_member")

package org.sdkotlin.koin.it.hello

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension
import org.koin.test.KoinTest
import org.koin.test.inject
import org.koin.test.junit5.KoinTestExtension
import org.sdkotlin.koin.hello.HelloController
import org.sdkotlin.koin.hello.helloModule
import org.sdkotlin.koin.it.hello.test.TestGreetingService
import org.sdkotlin.koin.it.hello.test.testHelloModule

internal class HelloControllerIT : KoinTest {

	private val helloController: HelloController by inject()

	@JvmField
	@RegisterExtension
	val koinTestExtension = KoinTestExtension.create {
		modules(listOf(helloModule, testHelloModule))
	}

	@Test
	fun `Integration test the hello controller`() {

		val response = helloController.sayHello()

		assertThat(response).isEqualTo(TestGreetingService.TEST_GREETING)
	}
}
