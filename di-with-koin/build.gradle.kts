import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.sdkotlin.build.kotlin-project")
	id("org.sdkotlin.build.test.integration-test-suite")
	id("org.sdkotlin.build.test.unit-test-suite")
	id("org.sdkotlin.build.mockito-kotlin-project")
}

dependencies {

	api(platform("org.sdkotlin.platforms:app-platform"))

	api(libs.bundles.koin.jvm)

	compileOnly(libs.jetbrains.annotations)

	integrationTestImplementation(platform("org.sdkotlin.platforms:test-platform"))

	integrationTestImplementation(libs.bundles.koin.test.junit5)
}

tasks {

	withType<KotlinCompile>().configureEach {
		kotlinOptions.freeCompilerArgs += "-Xinline-classes"
		kotlinOptions.freeCompilerArgs += "-opt-in=kotlin.RequiresOptIn"
	}
}
