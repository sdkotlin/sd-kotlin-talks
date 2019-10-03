package org.sdkotlin.koin.hello

import org.koin.core.KoinComponent
import org.koin.core.inject

internal class HelloApp : KoinComponent {

	private val helloController: HelloController by inject()

	fun run() = println(helloController.sayHello())
}
