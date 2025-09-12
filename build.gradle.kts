import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
import com.github.benmanes.gradle.versions.updates.gradle.GradleReleaseChannel.CURRENT
import org.gradle.api.tasks.wrapper.Wrapper.DistributionType.ALL

plugins {
	id("base")
	alias(libs.plugins.dependencyAnalysis.gradlePlugin)
	// Declare with `apply false` to include in Versions Gradle Plugin checks.
	alias(libs.plugins.detekt.gradlePlugin) apply false
	// Declare with `apply false` to include in Versions Gradle Plugin checks.
	alias(libs.plugins.jvmDependencyConflictDetection.gradlePlugin) apply false
	// Declare with `apply false` to include in Versions Gradle Plugin checks.
	alias(libs.plugins.jvmDependencyConflictResolution.gradlePlugin) apply false
	// Kotlin plugin declaration needed here for the Dependency Analysis Plugin,
	// but with `apply false` since the root project itself isn't a Kotlin
	// project:
	// https://github.com/autonomousapps/dependency-analysis-android-gradle-plugin/wiki/FAQ#typenotpresentexception-type-orgjetbrainskotlingradledslkotlinprojectextension-in-kotlin-jvm-library
	alias(libs.plugins.kotlin.gradlePlugin) apply false
	// Declare with `apply false` to include in Versions Gradle Plugin checks.
	alias(libs.plugins.mokkery.gradlePlugin) apply false
	alias(libs.plugins.versionsCheck.gradlePlugin)
}

dependencyAnalysis {
	issues {
		all {
			onAny {
				severity("fail")
			}
			onUnusedDependencies {
				exclude(
					// Test dependencies added globally for convenience.
					"org.assertj:assertj-core",
					"org.junit.jupiter:junit-jupiter",
					"org.junit.jupiter:junit-jupiter-api",
					"org.junit.jupiter:junit-jupiter-params",
				)
			}
			onIncorrectConfiguration {
				// https://github.com/autonomousapps/dependency-analysis-gradle-plugin/issues/1059
				severity("fail")
				exclude("org.jetbrains.kotlin:kotlin-stdlib")
			}
		}
	}
	usage {
		analysis {
			checkSuperClasses(true)
		}
	}
	useTypesafeProjectAccessors(true)
}

tasks {
	named<DependencyUpdatesTask>("dependencyUpdates").configure {
		rejectVersionIf {
			isNonStable(candidate.version) && !isNonStable(currentVersion)
		}
		gradleReleaseChannel = CURRENT.id
	}

	named<Wrapper>("wrapper").configure {
		gradleVersion = "9.1.0-rc-3"
		distributionType = ALL
	}
}

fun isNonStable(version: String): Boolean {
	val stableKeyword = listOf("RELEASE", "FINAL", "GA").any {
		version.uppercase().contains(it)
	}
	val regex = "^[0-9,.v-]+(-r)?$".toRegex()
	val isStable = stableKeyword || regex.matches(version)
	return isStable.not()
}
