import org.gradle.api.tasks.testing.logging.TestLogEvent.FAILED
import org.gradle.api.tasks.testing.logging.TestLogEvent.PASSED
import org.gradle.api.tasks.testing.logging.TestLogEvent.SKIPPED
import org.gradle.api.tasks.testing.logging.TestLogEvent.STANDARD_ERROR
import org.gradle.api.tasks.testing.logging.TestLogEvent.STANDARD_OUT

plugins {
	java
	`jvm-test-suite`
}

@Suppress("UnstableApiUsage")
testing {
	suites {
		named<JvmTestSuite>("test") {

			// Version catalog not available in precompiled script plugins:
			// https://github.com/gradle/gradle/issues/15383
			//val junitVersion = libs.version.junit
			val junitVersion = "5.9.1"

			useJUnitJupiter(junitVersion)

			dependencies {

				// Version catalog not available in precompiled script plugins:
				// https://github.com/gradle/gradle/issues/15383

				implementation(
					project.dependencies.platform(
						"org.sdkotlin.platforms:test-platform"
					)
				)

				//implementation(libs.assertj.core)
				implementation("org.assertj:assertj-core")

				//implementation(libs.bundles.junit)
				implementation("org.junit.jupiter:junit-jupiter-params")
			}

			targets {
				all {
					testTask.configure {
						testLogging {
							showStandardStreams = true
							events(
								PASSED,
								SKIPPED,
								FAILED,
								STANDARD_OUT,
								STANDARD_ERROR
							)
						}
					}
				}
			}
		}
	}
}
