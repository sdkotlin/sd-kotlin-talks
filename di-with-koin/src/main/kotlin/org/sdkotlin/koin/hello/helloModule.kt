package org.sdkotlin.koin.hello

import org.koin.dsl.module

val helloModule = module {

	single<GreetingService> { RandomGreetingService() }

	single<HelloController> { SimpleHelloController(get()) }
}
