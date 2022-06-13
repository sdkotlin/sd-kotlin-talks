import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.sdkotlin.build.kotlin-project")
	id("org.sdkotlin.build.junit-project")
	id("org.sdkotlin.build.assertj-project")
	id("org.sdkotlin.build.mockito-kotlin-project")
	id("org.sdkotlin.build.integration-test-suite")
}

dependencies {

	implementation(platform("org.sdkotlin.platforms:app-platform"))

	implementation(libs.bundles.koin.jvm)

	compileOnly(libs.jetbrains.annotations)

	"integrationTestImplementation"(platform("org.sdkotlin.platforms:test-platform"))

	"integrationTestImplementation"(libs.bundles.koin.test.junit5)
}

tasks {

	withType<KotlinCompile>().configureEach {
		kotlinOptions.freeCompilerArgs += "-Xinline-classes"
		kotlinOptions.freeCompilerArgs += "-opt-in=kotlin.RequiresOptIn"
	}
}
