dependencyResolutionManagement {
	@Suppress("UnstableApiUsage")
	repositories {
		mavenCentral()
		// https://github.com/InsertKoinIO/koin/issues/1004
		jcenter()
	}
}

includeBuild("platform")
includeBuild("build-logic")

rootProject.name = "sd-kotlin-talks"

include("kotlin-for-java-devs")
//include("effective-kotlin")
//include("tdd-in-kotlin")
//include("di-with-koin")
//include("kotlin-dl")
