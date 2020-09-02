import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	`kotlin-project`
	`junit-project`
	`assertj-project`
}

dependencies {
	compileOnly("org.jetbrains:annotations:20.1.0")
}

tasks {
	withType<KotlinCompile>().configureEach {
		kotlinOptions.freeCompilerArgs += "-Xinline-classes"
	}
}
