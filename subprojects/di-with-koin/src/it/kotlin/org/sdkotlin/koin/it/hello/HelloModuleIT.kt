package org.sdkotlin.koin.it.hello

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatExceptionOfType
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension
import org.koin.core.error.InstanceCreationException
import org.koin.core.qualifier.named
import org.koin.test.KoinTest
import org.koin.test.get
import org.koin.test.inject
import org.koin.test.junit5.KoinTestExtension
import org.sdkotlin.koin.hello.DECLARED_COMPONENT
import org.sdkotlin.koin.hello.DECLARED_COMPONENT_CONTAINER
import org.sdkotlin.koin.hello.ExternalComponent
import org.sdkotlin.koin.hello.ExternalComponentContainer
import org.sdkotlin.koin.hello.HelloController
import org.sdkotlin.koin.hello.RandomGreetingService
import org.sdkotlin.koin.hello.helloModule

private const val TESTING = "testing"

internal class HelloModuleIT : KoinTest {

	private val helloController: HelloController by inject()

	@JvmField
	@RegisterExtension
	val koinTestExtension = KoinTestExtension.create {
		modules(helloModule)
	}

	@Test
	fun `test the hello module`() {

		val response = helloController.sayHello()

		assertThat(response).isIn(RandomGreetingService.GREETINGS)
	}

	@Test
	fun `test declaring an external component`() {

		assertThatExceptionOfType(InstanceCreationException::class.java).isThrownBy {
			get<ExternalComponentContainer>(named(DECLARED_COMPONENT_CONTAINER))
		}

		val externalComponent = ExternalComponent(TESTING)

		getKoin().declare(externalComponent, named(DECLARED_COMPONENT))

		val externalComponentContainer =
			get<ExternalComponentContainer>(named(DECLARED_COMPONENT_CONTAINER))

		assertThat(externalComponentContainer.externalComponent.value)
			.isEqualTo(TESTING)
	}
}
