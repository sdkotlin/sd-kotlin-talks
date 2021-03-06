dependencyResolutionManagement {
	@Suppress("UnstableApiUsage")
	repositories {
		gradlePluginPortal()
	}
}

includeBuild("../platform")

rootProject.name = "build-logic"
