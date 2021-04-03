dependencyResolutionManagement {
	@Suppress("UnstableApiUsage")
	repositories {
		mavenCentral()
		jcenter()
	}
}

rootProject.name = "sd-kotlin-talks"

include("kotlin-for-java-devs")
include("effective-kotlin")
include("tdd-in-kotlin")
include("di-with-koin")
include("kotlin-dl")
