import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	java
	kotlin("jvm")
	// Version catalog not available in precompiled script plugins:
	// https://github.com/gradle/gradle/issues/15383
	// alias(libs.plugins.dependency.analysis.gradle.plugin)
	id("com.autonomousapps.dependency-analysis")
}

val javaTargetVersion = JavaVersion.VERSION_17
val kotlinTargetVersion = "1.8"

dependencies {

	runtimeOnly(kotlin("reflect"))
}

java {
	sourceCompatibility = javaTargetVersion
	targetCompatibility = javaTargetVersion
}

tasks {
	withType<JavaCompile>().configureEach {
		options.compilerArgs.add("--enable-preview")
	}

	withType<Test>().configureEach {
		jvmArgs("--enable-preview")
	}

	withType<JavaExec>().configureEach {
		jvmArgs("--enable-preview")
	}

	withType<KotlinCompile>().configureEach {
		kotlinOptions {
			freeCompilerArgs = listOf(
				"-Xjsr305=strict",
				"-opt-in=kotlin.ExperimentalStdlibApi",
			)
			languageVersion = kotlinTargetVersion
			apiVersion = "1.7"
			jvmTarget = javaTargetVersion.toString()
		}
	}
}
