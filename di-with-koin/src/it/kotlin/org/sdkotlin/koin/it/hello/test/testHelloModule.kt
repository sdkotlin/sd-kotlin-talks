package org.sdkotlin.koin.it.hello.test

import org.koin.dsl.module
import org.sdkotlin.koin.hello.GreetingService

internal val testHelloModule = module {

	single<GreetingService> { TestGreetingService() }
}
