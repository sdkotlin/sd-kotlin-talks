plugins {
	// Not using `id("kotlin-dsl")` per:
	// https://github.com/gradle/gradle/issues/23884
	`kotlin-dsl`
}

group = "org.sdkotlin.buildlogic"
version = "1.0.0-SNAPSHOT"

dependencies {

	implementation(platform("org.sdkotlin.platforms:plugins-platform"))

	implementation(libs.dependency.analysis.gradle.plugin.dependency)
	implementation(libs.detekt.gradle.plugin.dependency)
	implementation(libs.jvm.dependency.conflict.resolution.gradle.plugin.dependency)
	implementation(libs.kotlin.gradle.plugin.dependency)
	implementation(libs.ksp.gradle.plugin.dependency)
	implementation(libs.mokkery.gradle.plugin.dependency)
}
