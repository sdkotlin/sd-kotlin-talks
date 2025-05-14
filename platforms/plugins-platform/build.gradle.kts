plugins {
	id("java-platform")
}

dependencies {
	constraints {
		api(libs.dependencyAnalysis.gradlePluginDependency)
		api(libs.jvmDependencyConflictResolution.gradlePluginDependency)
		api(libs.kotlin.gradlePluginDependency)
		api(libs.ksp.gradlePluginDependency)
	}
}
