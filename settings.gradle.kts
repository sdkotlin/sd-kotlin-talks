pluginManagement {
	repositories {
		mavenCentral()
		gradlePluginPortal()
	}

	includeBuild("build-logic")
}

plugins {
	id("com.gradle.enterprise") version "3.16.2"
}

dependencyResolutionManagement {
	@Suppress("UnstableApiUsage")
	repositories {
		mavenCentral()
	}
}

enableFeaturePreview("STABLE_CONFIGURATION_CACHE")
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

gradleEnterprise {
	if (System.getenv("CI") != null) {
		buildScan {
			publishAlways()
			termsOfServiceUrl = "https://gradle.com/terms-of-service"
			termsOfServiceAgree = "yes"
		}
	}
}

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
include("typed-errors-in-kotlin")
