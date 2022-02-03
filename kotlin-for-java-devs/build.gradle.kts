import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	`kotlin-project`
	`junit-project`
	`assertj-project`
}

dependencies {
	val kotlinxCoroutinesVersion = "1.6.0"

	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlinxCoroutinesVersion")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-jvm:$kotlinxCoroutinesVersion")

	compileOnly("org.jetbrains:annotations:23.0.0")

	testImplementation("nl.jqno.equalsverifier:equalsverifier:3.9")
}
repositories {
	mavenCentral()
}

tasks {
	withType<KotlinCompile>().configureEach {
		kotlinOptions.freeCompilerArgs += "-Xinline-classes"
	}
}
