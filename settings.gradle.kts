pluginManagement {
	@Suppress("UnstableApiUsage")
	includeBuild("build-logic")
}

dependencyResolutionManagement {
	@Suppress("UnstableApiUsage")
	repositories {
		mavenCentral()
	}
}

includeBuild("platforms")

rootProject.name = "sd-kotlin-talks"

include("di-with-koin")
include("effective-kotlin")
include("kotlin-dl")
include("kotlin-for-java-devs")
include("kotlin-for-java-devs-client")
include("sorting-in-kotlin")
include("tdd-in-kotlin")
