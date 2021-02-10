import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	`kotlin-project`
	`junit-project`
	`assertj-project`
}

dependencies {
	val kotlinxCoroutinesVersion = "1.4.2"
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlinxCoroutinesVersion")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-jvm:$kotlinxCoroutinesVersion")

	compileOnly("org.jetbrains:annotations:20.1.0")

	testImplementation("nl.jqno.equalsverifier:equalsverifier:3.5.3")
}

tasks {
	withType<KotlinCompile>().configureEach {
		kotlinOptions.freeCompilerArgs += "-Xinline-classes"
	}
}
