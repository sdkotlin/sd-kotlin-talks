import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.sdkotlin.plugins.kotlin-project")
	id("org.sdkotlin.plugins.junit-project")
	id("org.sdkotlin.plugins.assertj-project")
}

dependencies {
	val kotlinxCoroutinesVersion = "1.4.3"
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlinxCoroutinesVersion")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-jvm:$kotlinxCoroutinesVersion")

	compileOnly("org.jetbrains:annotations:20.1.0")

	testImplementation("nl.jqno.equalsverifier:equalsverifier:3.5.5")
}

tasks {
	withType<KotlinCompile>().configureEach {
		kotlinOptions.freeCompilerArgs += "-Xinline-classes"
	}
}
