import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	`kotlin-project`
	`junit-project`
	`assertj-project`
	`integration-test-suite`
}

dependencies {
	val koinVersion = "2.2.2"

	implementation("org.koin:koin-core:$koinVersion")

	compileOnly("org.jetbrains:annotations:20.1.0")

	testImplementation("org.koin:koin-test-junit5:$koinVersion")
	testImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:2.2.0")
}

tasks {

	withType<KotlinCompile>().configureEach {
		kotlinOptions.freeCompilerArgs += "-Xinline-classes"
		kotlinOptions.freeCompilerArgs += "-Xopt-in=kotlin.RequiresOptIn"
	}
}
