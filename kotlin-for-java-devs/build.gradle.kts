import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.sdkotlin.buildlogic.kotlin-project")
	id("org.sdkotlin.buildlogic.test.unit-test-suite")
}

greeting {
	message.set("Hello from project")
}

dependencies {

	api(platform("org.sdkotlin.platforms:app-platform"))

	api(libs.bundles.arrow.jvm)

	implementation(libs.bundles.kotlinx.coroutines.jvm)

	compileOnly(libs.jetbrains.annotations)

	testImplementation(platform("org.sdkotlin.platforms:test-platform"))

	testImplementation(libs.equalsverifier)
}

tasks {
	withType<KotlinCompile>().configureEach {
		kotlinOptions.freeCompilerArgs += "-Xinline-classes"
	}
}
