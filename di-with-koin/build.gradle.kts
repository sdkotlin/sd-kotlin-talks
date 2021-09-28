import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	`kotlin-project`
	`junit-project`
	`assertj-project`
	`mockito-kotlin-project`
	`integration-test-suite`
}

dependencies {

	val koinVersion = "3.1.2"

	implementation("io.insert-koin:koin-core:$koinVersion")
	implementation("io.insert-koin:koin-core-jvm:$koinVersion")

	compileOnly("org.jetbrains:annotations:22.0.0")

	"integrationTestImplementation"("io.insert-koin:koin-test-junit5:$koinVersion")
}

tasks {

	withType<KotlinCompile>().configureEach {
		kotlinOptions.freeCompilerArgs += "-Xinline-classes"
		kotlinOptions.freeCompilerArgs += "-Xopt-in=kotlin.RequiresOptIn"
	}
}
