package org.sdkotlin.koin.hello

import org.koin.core.component.KoinApiExtension
import org.koin.core.context.startKoin

@KoinApiExtension
fun main() {

	startKoin {
		modules(helloModule)
	}

	HelloApp().run()
}
