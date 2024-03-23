import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	kotlin("jvm")
	// Version catalog type-safe accessors and extension not yet available in
	// plugins block of precompiled script plugins:
	// https://github.com/gradle/gradle/issues/15383
	// alias(libs.plugins.dependency.analysis.gradle.plugin)
	id("com.autonomousapps.dependency-analysis")
}

val javaTargetVersion: String = JavaVersion.VERSION_21.toString()

kotlin {
	jvmToolchain {
		languageVersion.set(
			JavaLanguageVersion.of(javaTargetVersion)
		)
	}
}

dependencies {

	runtimeOnly(kotlin("reflect"))
}

tasks {
	withType<JavaCompile>().configureEach {

		with(options) {
			release = javaTargetVersion.toInt()
			isFork = true
		}
	}

	withType<KotlinCompile>().configureEach {
		compilerOptions {
			optIn = listOf(
				"kotlin.ExperimentalStdlibApi",
				"kotlin.contracts.ExperimentalContracts",
			)
			// Planned for deprecation:
			// https://youtrack.jetbrains.com/issue/KT-61035/
			freeCompilerArgs = listOf(
				// https://youtrack.jetbrains.com/issue/KT-61410/
				"-Xjsr305=strict",
				// https://youtrack.jetbrains.com/issue/KT-49746/
				"-Xjdk-release=$javaTargetVersion"
			)
		}
	}

	withType<JavaExec>().configureEach {

		if (name.endsWith("main()")) {

			// https://github.com/gradle/gradle/issues/21364
			notCompatibleWithConfigurationCache("JavaExec created by IntelliJ")
		}
	}
}
