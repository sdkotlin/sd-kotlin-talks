import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	`java-library`
	kotlin("jvm")
	// Version catalog not available in precompiled script plugins:
	// https://github.com/gradle/gradle/issues/15383
	// alias(libs.plugins.dependency.analysis.gradle.plugin)
	id("com.autonomousapps.dependency-analysis")
}

val javaTargetVersion = JavaVersion.VERSION_17.toString()
val kotlinTargetVersion = "1.8"

kotlin {
	jvmToolchain {
		languageVersion.set(JavaLanguageVersion.of(javaTargetVersion))
	}
}

dependencies {

	runtimeOnly(kotlin("reflect"))
}

tasks {
	withType<JavaCompile>().configureEach {
		sourceCompatibility = javaTargetVersion
		targetCompatibility = javaTargetVersion
		options.compilerArgs.add("--enable-preview")
	}

	withType<KotlinCompile>().configureEach {
		kotlinOptions {
			languageVersion = kotlinTargetVersion
			apiVersion = kotlinTargetVersion
			jvmTarget = javaTargetVersion
			freeCompilerArgs = listOf(
				"-Xjsr305=strict",
				"-opt-in=kotlin.ExperimentalStdlibApi",
			)
		}
	}

	withType<Test>().configureEach {
		jvmArgs("--enable-preview")
	}

	withType<JavaExec>().configureEach {
		jvmArgs("--enable-preview")
	}
}
