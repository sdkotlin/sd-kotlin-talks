package org.sdkotlin.koin.hello

class SimpleHelloController(private val greetingService: GreetingService) :
	HelloController {

	override fun sayHello() = greetingService.getGreeting()
}
