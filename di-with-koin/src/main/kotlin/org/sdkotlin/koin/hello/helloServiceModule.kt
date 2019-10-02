package org.sdkotlin.koin.hello

import org.koin.dsl.module

val helloServiceModule = module {
	single { HelloService() }
}
