package org.sdkotlin.koin.hello

import org.koin.core.qualifier.named
import org.koin.dsl.module

const val DECLARED_COMPONENT = "declared component"
const val DECLARED_COMPONENT_CONTAINER = "declared component container"
const val REVERSE_INJECTED_COMPONENT = "reverse injected component"
const val REVERSE_INJECTED_COMPONENT_CONTAINER = "reverse injected component container"

internal val helloModule = module {

	single<GreetingService> { RandomGreetingService() }
	single<GreetingService>(named<EnglishGreetingService>()) { EnglishGreetingService() }

	single<HelloController> { SimpleHelloController(get()) }

	// Injection will fail if DECLARED_COMPONENT isn't externally declared prior, e.g. with getKoin().declare(...)
	single(named(DECLARED_COMPONENT_CONTAINER)) {
		ExternalComponentContainer(
			get(named(DECLARED_COMPONENT))
		)
	}

	// A parameterized component where the parameter ends up being the component itself
	single(named(REVERSE_INJECTED_COMPONENT)) { (externalComponent: ExternalComponent) ->
		externalComponent
	}

	// Injection will fail if REVERSE_INJECTED_COMPONENT isn't reverse injected prior
	single(named(REVERSE_INJECTED_COMPONENT_CONTAINER)) {
		ExternalComponentContainer(
			get(named(REVERSE_INJECTED_COMPONENT))
		)
	}
}
