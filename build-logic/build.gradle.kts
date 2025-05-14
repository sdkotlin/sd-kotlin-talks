plugins {
	// Not using `id("kotlin-dsl")` per:
	// https://github.com/gradle/gradle/issues/23884
	`kotlin-dsl`
}

group = "org.sdkotlin.buildlogic"
version = "1.0.0-SNAPSHOT"

dependencies {

	implementation(platform("org.sdkotlin.platforms:plugins-platform"))

	implementation(libs.dependencyAnalysis.gradlePluginDependency)
	implementation(libs.detekt.gradlePluginDependency)
	implementation(libs.jvmDependencyConflictResolution.gradlePluginDependency)
	implementation(libs.kotlin.gradlePluginDependency)
	implementation(libs.ksp.gradlePluginDependency)
	implementation(libs.mokkery.gradlePluginDependency)
}
