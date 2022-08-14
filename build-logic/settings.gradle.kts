dependencyResolutionManagement {
	@Suppress("UnstableApiUsage")
	repositories {
		gradlePluginPortal()
	}
	@Suppress("UnstableApiUsage")
	versionCatalogs {
		create("libs") {
			from(files("../gradle/libs.versions.toml"))
		}
	}
}

includeBuild("../platforms")

rootProject.name = "build-logic"
