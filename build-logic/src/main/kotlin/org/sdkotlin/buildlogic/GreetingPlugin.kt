package org.sdkotlin.buildlogic

import org.gradle.api.Plugin
import org.gradle.api.Project

class GreetingPlugin : Plugin<Project> {
	override fun apply(project: Project) {
		project.task("hello") {
			doLast {
				println("Hello from the GreetingPlugin")
			}
		}
	}
}
