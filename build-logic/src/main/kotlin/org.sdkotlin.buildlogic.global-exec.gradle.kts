import org.sdkotlin.buildlogic.globalexec.BuildScopedGreetingService
import org.sdkotlin.buildlogic.globalexec.ProjectScopedGreetingTask

gradle.sharedServices.registerIfAbsent(
	BuildScopedGreetingService.SERVICE_NAME,
	BuildScopedGreetingService::class.java
) {
	parameters.greeting.set("Doctor")
}

tasks.register<ProjectScopedGreetingTask>("greetings")
