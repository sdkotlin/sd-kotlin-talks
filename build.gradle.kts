import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
import com.github.benmanes.gradle.versions.updates.gradle.GradleReleaseChannel.CURRENT
import org.gradle.api.tasks.wrapper.Wrapper.DistributionType.ALL
import java.util.Locale

// Without these suppressions version catalog usage here and in other build
// files is marked red by IntelliJ:
// https://github.com/gradle/gradle/issues/22797.
@Suppress(
	"DSL_SCOPE_VIOLATION",
	"MISSING_DEPENDENCY_CLASS",
	"UNRESOLVED_REFERENCE_WRONG_RECEIVER",
	"FUNCTION_CALL_EXPECTED"
)
plugins {
	alias(libs.plugins.dependency.analysis.gradle.plugin)
	// Kotlin plugin declaration needed here for the Dependency Analysis Plugin,
	// but with `apply false` since the root project itself isn't a Kotlin
	// project:
	// https://github.com/autonomousapps/dependency-analysis-android-gradle-plugin/wiki/FAQ#typenotpresentexception-type-orgjetbrainskotlingradledslkotlinprojectextension-in-kotlin-jvm-library
	alias(libs.plugins.kotlin.gradle.plugin) apply false
	alias(libs.plugins.versions.gradle.plugin)
}

allprojects {
	group = "org.sdkotlin"
	version = "1.0-SNAPSHOT"
}

subprojects {
	tasks {
		register<DependencyReportTask>("allDependencies") {
			description = "Display dependencies report for all subprojects."
			group = "help"
		}
	}
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
					"org.junit.jupiter:junit-jupiter-api",
					"org.junit.jupiter:junit-jupiter-params",
				)
			}
		}
	}
}

tasks {
	withType<DependencyUpdatesTask>().configureEach {
		rejectVersionIf {
			isNonStable(candidate.version)
		}
		gradleReleaseChannel = CURRENT.id
	}

	named<Wrapper>("wrapper").configure {
		gradleVersion = "8.0.1"
		distributionType = ALL
	}
}

fun isNonStable(version: String): Boolean {
	val stableKeyword = listOf("RELEASE", "FINAL", "GA").any {
		version.uppercase(Locale.getDefault())
			.contains(it)
	}
	val unstableKeyword =
		listOf("""M\d+""").any {
			version.uppercase(Locale.getDefault()).contains(it.toRegex())
		}
	val regex = "^[0-9,.v-]+(-r)?$".toRegex()
	val isStable = (stableKeyword && !unstableKeyword) || regex.matches(version)
	return isStable.not()
}
