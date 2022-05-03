import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	`kotlin-project`
	`junit-project`
	`assertj-project`
}

dependencies {
	val arrowKtVersion = "1.1.2"
	val kotlinxCoroutinesVersion = "1.6.1"

	api("io.arrow-kt:arrow-core:$arrowKtVersion")
	api("io.arrow-kt:arrow-core-jvm:$arrowKtVersion")

	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlinxCoroutinesVersion")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-jvm:$kotlinxCoroutinesVersion")

	compileOnly("org.jetbrains:annotations:23.0.0")

	testImplementation("nl.jqno.equalsverifier:equalsverifier:3.10")
}

tasks {
	withType<KotlinCompile>().configureEach {
		kotlinOptions.freeCompilerArgs += "-Xinline-classes"
	}
}
