import gradle.kotlin.dsl.accessors._e955592cfcca1783c48ac959ec339844.implementation
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	kotlin("jvm")
}

dependencies {
	val kotlinPoetVersion = "1.11.0"

	implementation("com.squareup:kotlinpoet:$kotlinPoetVersion")
	implementation("com.squareup:kotlinpoet-ksp:$kotlinPoetVersion")
}

tasks {
	withType<KotlinCompile>().configureEach {
		kotlinOptions.freeCompilerArgs +=
			"-opt-in=com.squareup.kotlinpoet.ksp.KotlinPoetKspPreview"
	}
}
