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

gradle.beforeProject {
	// Set group and version properties for all projects
	group = "org.sdkotlin"
	version = "1.0.0-SNAPSHOT"
}

include("subprojects:di-with-koin")
include("subprojects:effective-kotlin")
include("subprojects:kotlin-dl")
include("subprojects:kotlin-for-java-devs")
include("subprojects:kotlin-for-java-devs-client")
include("subprojects:ksp-builder-generator:api:annotations")
include("subprojects:ksp-builder-generator:api:builder")
include("subprojects:ksp-builder-generator:processor")
include("subprojects:ksp-builder-generator:processor:test-project")
include("subprojects:sorting-in-kotlin")
include("subprojects:tdd-in-kotlin")
include("subprojects:typed-errors-in-kotlin")
