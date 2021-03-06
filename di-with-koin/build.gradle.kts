import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.sdkotlin.plugins.kotlin-project")
	id("org.sdkotlin.plugins.junit-project")
	id("org.sdkotlin.plugins.assertj-project")
	id("org.sdkotlin.plugins.mockito-kotlin-project")
	id("org.sdkotlin.plugins.integration-test-suite")
}

dependencies {
	val koinVersion = "2.2.2"

	implementation("org.koin:koin-core:$koinVersion")

	compileOnly("org.jetbrains:annotations:20.1.0")

	testImplementation("org.koin:koin-test-junit5:$koinVersion")
}

tasks {

	withType<KotlinCompile>().configureEach {
		kotlinOptions.freeCompilerArgs += "-Xinline-classes"
		kotlinOptions.freeCompilerArgs += "-Xopt-in=kotlin.RequiresOptIn"
	}
}
