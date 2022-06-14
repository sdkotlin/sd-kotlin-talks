plugins {
	`kotlin-dsl`
}

dependencies {

	implementation(platform("org.sdkotlin.platforms:plugins-platform"))

	implementation(libs.kotlin.gradle.plugin.dependency)
	implementation(libs.testsets.gradle.plugin.dependency)
}
