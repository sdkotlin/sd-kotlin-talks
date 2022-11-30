package org.sdkotlin.buildlogic

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.provider.Property
import org.gradle.kotlin.dsl.create

interface GreetingPluginExtension {
	val message: Property<String>
}

class GreetingPlugin : Plugin<Project> {
	override fun apply(project: Project) {

		val extension = project.extensions.create(
			name = "greeting",
			type = GreetingPluginExtension::class,
		)

		extension.message.convention("Hello from GreetingPlugin")

		project.task("hello") {
			doLast {
				println(extension.message.get())
			}
		}
	}
}
