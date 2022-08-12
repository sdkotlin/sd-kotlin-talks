import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	java
	kotlin("jvm")
	// Version catalog not available in precompiled script plugins:
	// https://github.com/gradle/gradle/issues/15383
	// alias(libs.plugins.dependency.analysis.gradle.plugin)
	id("com.autonomousapps.dependency-analysis")
}

dependencies {

	runtimeOnly(kotlin("reflect"))
}

java {
	sourceCompatibility = JavaVersion.VERSION_17
	targetCompatibility = JavaVersion.VERSION_17
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
			languageVersion = "1.6"
			apiVersion = "1.6"
			jvmTarget = JavaVersion.VERSION_16.toString()
		}
	}
}
