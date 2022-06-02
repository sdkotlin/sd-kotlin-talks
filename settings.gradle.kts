dependencyResolutionManagement {
	@Suppress("UnstableApiUsage")
	repositories {
		mavenCentral()
	}
}

rootProject.name = "sd-kotlin-talks"

include("di-with-koin")
include("effective-kotlin")
include("kotlin-dl")
include("kotlin-for-java-devs")
include("kotlin-for-java-devs-client")
include("ksp:annotations")
include("ksp:ksp-processed")
include("ksp:ksp-processor")
include("sorting-in-kotlin")
include("tdd-in-kotlin")
