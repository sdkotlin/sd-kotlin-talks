package org.sdkotlin.koin.hello

import org.koin.core.context.startKoin

fun main() {

	startKoin {
		modules(helloServiceModule)
	}

	HelloApp().run()
}
