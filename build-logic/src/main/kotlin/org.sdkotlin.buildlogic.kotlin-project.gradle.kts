import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	kotlin("jvm")
	// Version catalog type-safe accessors and extension not yet available in
	// plugins block of precompiled script plugins:
	// https://github.com/gradle/gradle/issues/15383
	// alias(libs.plugins.dependency.analysis.gradle.plugin)
	id("com.autonomousapps.dependency-analysis")
}

val javaTargetVersion: String = JavaVersion.VERSION_17.toString()

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
		sourceCompatibility = javaTargetVersion
		targetCompatibility = javaTargetVersion

		with(options) {

			isFork = true
			compilerArgs.add("--enable-preview")
		}
	}

	withType<KotlinCompile>().configureEach {
		compilerOptions {
			jvmTarget = JvmTarget.fromTarget(javaTargetVersion)
			optIn = listOf(
				"kotlin.ExperimentalStdlibApi",
				"kotlin.contracts.ExperimentalContracts",
			)
			// Planned for deprecation:
			// https://youtrack.jetbrains.com/issue/KT-61035/
			freeCompilerArgs = listOf(
				// https://youtrack.jetbrains.com/issue/KT-61410/
				"-Xjsr305=strict",
			)
		}
	}

	withType<Test>().configureEach {
		jvmArgs("--enable-preview")
	}

	withType<JavaExec>().configureEach {

		if (name.endsWith("main()")) {

			// https://github.com/gradle/gradle/issues/21364
			notCompatibleWithConfigurationCache("JavaExec created by IntelliJ")

			args("Testing")
		}

		jvmArgs("--enable-preview")
	}
}
