import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.sdkotlin.buildlogic.kotlin-project")
	id("org.sdkotlin.buildlogic.test.integration-test-suite")
	id("org.sdkotlin.buildlogic.test.unit-test-suite")
	id("org.sdkotlin.buildlogic.mockito-kotlin-project")
}

dependencies {

	api(platform("org.sdkotlin.platforms:app-platform"))

	implementation(libs.bundles.koin.jvm)

	compileOnly(libs.jetbrains.annotations)

	integrationTestImplementation(platform("org.sdkotlin.platforms:test-platform"))

	integrationTestImplementation(libs.bundles.koin.test.junit5)
}

tasks {

	withType<KotlinCompile>().configureEach {
		kotlinOptions.freeCompilerArgs += "-opt-in=kotlin.RequiresOptIn"
	}
}
