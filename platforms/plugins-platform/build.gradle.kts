plugins {
	id("java-platform")
}

group = "org.sdkotlin.platforms"

dependencies {
	constraints {
		api(libs.gradle.testsets.plugin)
		api(libs.kotlin.gradle.plugin)
	}
}
