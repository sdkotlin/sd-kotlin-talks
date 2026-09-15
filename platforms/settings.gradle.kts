pluginManagement {
	repositories {
		mavenCentral()
		gradlePluginPortal()
	}
}

plugins {
	// Produces the partial report that the root build's `dependencyUpdates`
	// task merges.
	id("io.github.ben-manes.versions.settings") version "0.63.1"
}

dependencyResolutionManagement {
	// A java-platform build resolves nothing of its own, but the plugin needs
	// a repository to determine candidate versions from.
	@Suppress("UnstableApiUsage")
	repositories {
		mavenCentral()
		// plugins-platform constrains Gradle plugin artifacts that only the
		// Gradle Plugin Portal publishes.
		gradlePluginPortal()
	}
	versionCatalogs {
		create("libs") {
			from(files("../gradle/libs.versions.toml"))
		}
	}
}

rootProject.name = "platforms"

gradle.beforeProject {
	// Set group and version properties for all projects
	group = "org.sdkotlin.platforms"
	version = "1.0.0-SNAPSHOT"
}

include("app-platform")
include("test-platform")
include("plugins-platform")
