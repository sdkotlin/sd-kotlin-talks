package org.sdkotlin.koin.hello

import org.koin.core.qualifier.named
import org.koin.dsl.module

const val DECLARED_COMPONENT = "declared component"
const val DECLARED_COMPONENT_CONTAINER = "declared component container"

internal val helloModule = module {

	// An unqualified GreetingService
	single<GreetingService> {
		RandomGreetingService()
	}

	// A named GreetingService
	single<GreetingService>(named<EnglishGreetingService>()) {
		EnglishGreetingService()
	}

	single<HelloController> {
		SimpleHelloController(get())
	}

	// Injection will fail if DECLARED_COMPONENT isn't externally declared
	// prior, e.g. with getKoin().declare(...)
	single(named(DECLARED_COMPONENT_CONTAINER)) {
		ExternalComponentContainer(
			get(named(DECLARED_COMPONENT))
		)
	}
}
