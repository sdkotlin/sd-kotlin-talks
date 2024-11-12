package org.sdkotlin.buildlogic.globalexec

import org.gradle.api.DefaultTask
import org.gradle.api.GradleException
import org.gradle.api.provider.Property
import org.gradle.api.services.BuildService
import org.gradle.api.services.BuildServiceParameters
import org.gradle.api.services.ServiceReference
import org.gradle.api.tasks.TaskAction
import org.gradle.kotlin.dsl.provideDelegate
import org.gradle.process.ExecOperations
import javax.inject.Inject

abstract class BuildScopedGreetingService :
	BuildService<BuildScopedGreetingService.Params> {
	interface Params : BuildServiceParameters {
		val greeting: Property<String>
	}

	companion object {
		const val SERVICE_NAME = "buildScopedGreetingService"
	}

	@get:Inject
	abstract val execOperations: ExecOperations

	val exitValue: Int by lazy {
		execOperations.exec {
			commandLine(
				buildCurrentOsCommand(
					"echo", "Hello, ${parameters.greeting.get()}"
				)
			)
			standardOutput = System.out
		}.exitValue
	}
}

abstract class ProjectScopedGreetingTask : DefaultTask() {
	init {
		group = "demo"
		description = "A project-scoped exec task that depends on a " +
			"build-scoped exec from a shared build service."
	}

	@Suppress("UnstableApiUsage")
	@get:ServiceReference(BuildScopedGreetingService.SERVICE_NAME)
	abstract val buildScopedGreetingService: Property<BuildScopedGreetingService>

	@get:Inject
	abstract val execOperations: ExecOperations

	@TaskAction
	fun doTaskAction() {

		val buildScopedGreetingServiceInstance: BuildScopedGreetingService =
			buildScopedGreetingService.get()

		val exitValue = buildScopedGreetingServiceInstance.exitValue

		if (exitValue != 0)
			throw GradleException(
				"Build service exec resulted in a non-zero exit value $exitValue!"
			)

		val greeting =
			buildScopedGreetingServiceInstance.parameters.greeting.get()

		execOperations.exec {
			commandLine(
				buildCurrentOsCommand("echo", greeting)
			)
			standardOutput = System.out
		}
	}
}

fun buildCurrentOsCommand(vararg arguments: String): List<String> =
	buildList {
		val isWindows = System.getProperty("os.name")
			.contains("windows", ignoreCase = true)
		if (isWindows) {
			add("cmd.exe")
			add("/c")
		}
		addAll(arguments)
	}
