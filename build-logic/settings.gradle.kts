pluginManagement {
	repositories {
		mavenCentral()
		gradlePluginPortal()
	}
}

plugins {
	id("org.gradle.toolchains.foojay-resolver-convention") version "0.7.0"
}

dependencyResolutionManagement {
	@Suppress("UnstableApiUsage")
	repositories {
		mavenCentral()
		gradlePluginPortal()
		maven {
			url = uri(
				"https://oss.sonatype.org/content/repositories/snapshots/"
			)
		}
	}
	versionCatalogs {
		create("libs") {
			from(files("../gradle/libs.versions.toml"))
		}
	}
}

includeBuild("../platforms")

rootProject.name = "build-logic"
