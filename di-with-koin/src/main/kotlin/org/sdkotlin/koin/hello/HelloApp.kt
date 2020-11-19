package org.sdkotlin.koin.hello

import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

@KoinApiExtension
internal class HelloApp : KoinComponent {

	private val helloController: HelloController by inject()

	fun run() = println(helloController.sayHello())
}
