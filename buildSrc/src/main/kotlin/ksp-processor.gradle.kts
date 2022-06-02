import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	kotlin("jvm")
}

dependencies {
	implementation("com.google.devtools.ksp:symbol-processing-api")
}
