pluginManagement {
	@Suppress("UnstableApiUsage")
	includeBuild("build-logic")
}

plugins {
	id("org.gradle.toolchains.foojay-resolver-convention") version "0.4.0"
}

dependencyResolutionManagement {
	@Suppress("UnstableApiUsage")
	repositories {
		mavenCentral()
	}
}

enableFeaturePreview("STABLE_CONFIGURATION_CACHE")

includeBuild("platforms")

rootProject.name = "sd-kotlin-talks"

include("di-with-koin")
include("effective-kotlin")
include("kotlin-dl")
include("kotlin-for-java-devs")
include("kotlin-for-java-devs-client")
include("sorting-in-kotlin")
include("tdd-in-kotlin")
