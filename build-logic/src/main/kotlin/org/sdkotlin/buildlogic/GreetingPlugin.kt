package org.sdkotlin.buildlogic

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.provider.Property
import org.gradle.api.provider.Provider
import org.gradle.api.provider.ProviderFactory
import org.gradle.kotlin.dsl.create
import javax.inject.Inject

abstract class GreetingExtension {

	@get:Inject
	internal abstract val providerFactory: ProviderFactory

	abstract val message: Property<String>

	val currentOs: Provider<String>
		get() = providerFactory.provider { "SuperOS" }
}

class GreetingPlugin : Plugin<Project> {

	override fun apply(project: Project) {

		val greetingExtension = project.extensions.create(
			name = "greeting",
			type = GreetingExtension::class,
		)

		greetingExtension.message.convention("Hello from GreetingPlugin")

		project.task("hello") {
			doLast {
				println(greetingExtension.message.get())
			}
		}
	}
}
