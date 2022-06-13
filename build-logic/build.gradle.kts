plugins {
	`kotlin-dsl`
}

dependencies {

	implementation(platform("org.sdkotlin.platforms:plugins-platform"))

	implementation(libs.kotlin.gradle.plugin)
	implementation(libs.gradle.testsets.plugin)
}
