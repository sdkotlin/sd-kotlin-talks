pluginManagement {
	repositories {
		mavenCentral()
		gradlePluginPortal()
	}
}

plugins {
	// Produces the partial report that the root build's `dependencyUpdates`
	// task merges.
	id("io.github.ben-manes.versions") version "0.66.0"
}

dependencyResolutionManagement {
	@Suppress("UnstableApiUsage")
	repositories {
		mavenCentral()
		gradlePluginPortal()
	}
	versionCatalogs {
		create("libs") {
			from(files("../gradle/libs.versions.toml"))
		}
	}
}

includeBuild("../platforms")
includeBuild("../detekt-rules")

rootProject.name = "build-logic"
