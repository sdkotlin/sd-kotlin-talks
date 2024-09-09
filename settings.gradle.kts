pluginManagement {
	repositories {
		mavenCentral()
		gradlePluginPortal()
	}

	includeBuild("build-logic")
}

plugins {
	id("com.gradle.develocity") version "3.18"
}

dependencyResolutionManagement {
	@Suppress("UnstableApiUsage")
	repositories {
		mavenCentral()
	}
}

enableFeaturePreview("STABLE_CONFIGURATION_CACHE")
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

develocity {
	buildScan {
		publishing.onlyIf { !System.getenv("CI").isNullOrEmpty() }
		termsOfUseUrl.set("https://gradle.com/help/legal-terms-of-use")
		termsOfUseAgree.set("yes")
	}
}

rootProject.name = "sd-kotlin-talks"

includeBuild("platforms")

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
include("typed-errors-in-kotlin")
