abstract class BuildScopedGreetingService @Inject constructor(
	execOperations: ExecOperations,
) : BuildService<BuildScopedGreetingService.Params> {

	companion object {
		const val SERVICE_NAME = "buildScopedGreetingService"
	}

	interface Params : BuildServiceParameters {
		val greeting: Property<String>
	}

	val exitValue: Int =
		execOperations.exec {
			commandLine("echo", "Hello, ${parameters.greeting.get()}")
			standardOutput = System.out
		}.exitValue
}

abstract class ProjectScopedGreetingTask @Inject constructor(
	private val execOperations: ExecOperations,
) : DefaultTask() {

	init {
		group = "demo"
		description = "A project-scoped exec task that depends on a " +
			"build-scoped exec from a shared build service."
	}

	@Suppress("UnstableApiUsage")
	@get:ServiceReference(BuildScopedGreetingService.SERVICE_NAME)
	abstract val buildScopedGreetingService: Property<BuildScopedGreetingService>

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
			commandLine("echo", greeting)
			standardOutput = System.out
		}
	}
}

gradle.sharedServices.registerIfAbsent(
	BuildScopedGreetingService.SERVICE_NAME,
	BuildScopedGreetingService::class.java
) {
	parameters.greeting.set("Doctor");
}

tasks.register<ProjectScopedGreetingTask>("greetings")
