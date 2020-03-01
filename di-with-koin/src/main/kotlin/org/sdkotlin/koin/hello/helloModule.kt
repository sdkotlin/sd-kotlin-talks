package org.sdkotlin.koin.hello

import org.koin.core.qualifier.named
import org.koin.dsl.module

val helloModule = module {

	single<GreetingService> { RandomGreetingService() }
	single<GreetingService>(named<EnglishGreetingService>()) { EnglishGreetingService() }

	single<HelloController> { SimpleHelloController(get()) }

	single(named<ExternalComponent>()) { (externalComponent: ExternalComponent) -> externalComponent }

	single(named<ExternalComponentContainer>()) { ExternalComponentContainer(get(named<ExternalComponent>())) }
}
