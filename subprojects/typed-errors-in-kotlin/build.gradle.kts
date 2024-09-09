import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.sdkotlin.buildlogic.kotlin-project")
	id("org.sdkotlin.buildlogic.test.unit-test-suite")
}

dependencies {

	api(platform("org.sdkotlin.platforms:app-platform"))

	api(libs.bundles.arrow.jvm)
}

tasks {
	withType<KotlinCompile>().configureEach {
		compilerOptions {
			freeCompilerArgs.add("-Xcontext-receivers")
		}
	}
}
