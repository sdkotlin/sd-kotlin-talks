pluginManagement {
	repositories {
		mavenCentral()
		gradlePluginPortal()
		maven {
			url = uri(
				"https://oss.sonatype.org/content/repositories/snapshots/"
			)
		}
	}

	includeBuild("build-logic")
}

plugins {
	id("org.gradle.toolchains.foojay-resolver-convention") version "0.7.0"
}

dependencyResolutionManagement {
	@Suppress("UnstableApiUsage")
	repositories {
		mavenCentral()
	}
}

enableFeaturePreview("STABLE_CONFIGURATION_CACHE")
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

includeBuild("platforms")

rootProject.name = "sd-kotlin-talks"

include("di-with-koin")
include("effective-kotlin")
include("kotlin-dl")
include("kotlin-for-java-devs")
include("kotlin-for-java-devs-client")
include("ksp-builder-generator:api:annotations")
include("ksp-builder-generator:api:builder")
include("ksp-builder-generator:processor")
include("ksp-builder-generator:processor:test-project")
include("sorting-in-kotlin")
include("tdd-in-kotlin")
