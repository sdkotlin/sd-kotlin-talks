package org.sdkotlin.buildlogic

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.provider.Property
import org.gradle.api.provider.Provider
import org.gradle.api.provider.ProviderFactory
import org.gradle.kotlin.dsl.create
import javax.inject.Inject

abstract class GreetingExtension(
	private val osDetectorCurrentOs: String,
) {

	@get:Inject
	internal abstract val providerFactory: ProviderFactory

	abstract val message: Property<String>

	val currentOs: Provider<String>
		get() = providerFactory.provider { osDetectorCurrentOs }
}

class GreetingPlugin : Plugin<Project> {

	override fun apply(project: Project) {

		val osDetectorCurrentOS = "SuperOS"

		val greetingExtension = project.extensions.create(
			name = "greeting",
			type = GreetingExtension::class,
			osDetectorCurrentOS
		)

		greetingExtension.message.convention("Hello from GreetingPlugin")

		project.task("hello") {
			doLast {
				println(greetingExtension.message.get())
			}
		}
	}
}