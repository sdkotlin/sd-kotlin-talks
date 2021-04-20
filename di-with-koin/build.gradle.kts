import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	`kotlin-project`
	`junit-project`
	`assertj-project`
	`mockito-kotlin-project`
	`integration-test-suite`
}

dependencies {
	val koinVersion = "3.0.1"

	implementation("io.insert-koin:koin-core:$koinVersion")

	compileOnly("org.jetbrains:annotations:20.1.0")

	testImplementation("io.insert-koin:koin-test-junit5:$koinVersion")
}

tasks {

	withType<KotlinCompile>().configureEach {
		kotlinOptions.freeCompilerArgs += "-Xinline-classes"
		kotlinOptions.freeCompilerArgs += "-Xopt-in=kotlin.RequiresOptIn"
	}
}
