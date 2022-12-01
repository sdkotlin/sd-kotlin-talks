package org.sdkotlin.buildlogic

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.provider.Property
import org.gradle.api.provider.Provider
import org.gradle.api.provider.ProviderFactory
import org.gradle.kotlin.dsl.create
import javax.inject.Inject

abstract class GreetingExtension(
	private val _otherMessage: String,
) {

	@get:Inject
	internal abstract val providerFactory: ProviderFactory

	abstract val message: Property<String>

	val otherMessage: Provider<String>
		get() = providerFactory.provider { _otherMessage }
}

class GreetingPlugin : Plugin<Project> {

	override fun apply(project: Project) {

		val otherMessage = "Hello, Other World!"

		val greetingExtension = project.extensions.create(
			name = "greeting",
			type = GreetingExtension::class,
			otherMessage
		)

		greetingExtension.message.convention("Hello from GreetingPlugin!")

		project.task("hello") {
			doLast {
				println(greetingExtension.message.get())
			}
		}
	}
}
