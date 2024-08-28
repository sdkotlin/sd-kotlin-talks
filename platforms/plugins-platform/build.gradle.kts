plugins {
	id("java-platform")
}

group = "org.sdkotlin.platforms"

dependencies {
	constraints {
		api(libs.dependency.analysis.gradle.plugin.dependency)
		api(libs.jvm.dependency.conflict.resolution.gradle.plugin.dependency)
		api(libs.kotlin.gradle.plugin.dependency)
		api(libs.ksp.gradle.plugin.dependency)
	}
}
