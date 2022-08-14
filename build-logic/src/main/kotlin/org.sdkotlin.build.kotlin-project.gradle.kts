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
val kotlinTargetVersion = "1.7"

dependencies {

	runtimeOnly(kotlin("reflect"))
}

java {
	sourceCompatibility = javaTargetVersion
	targetCompatibility = javaTargetVersion
}

tasks {
	withType<JavaCompile> {
		options.compilerArgs.add("--enable-preview")
	}

	withType<Test> {
		jvmArgs("--enable-preview")
	}

	withType<JavaExec> {
		jvmArgs("--enable-preview")
	}

	withType<KotlinCompile> {
		kotlinOptions {
			freeCompilerArgs = listOf("-Xjsr305=strict")
			languageVersion = kotlinTargetVersion
			apiVersion = kotlinTargetVersion
			jvmTarget = javaTargetVersion.toString()
		}
	}
}
