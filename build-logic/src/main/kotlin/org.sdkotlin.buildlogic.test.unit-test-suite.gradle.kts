import org.gradle.api.tasks.testing.logging.TestLogEvent.FAILED
import org.gradle.api.tasks.testing.logging.TestLogEvent.PASSED
import org.gradle.api.tasks.testing.logging.TestLogEvent.SKIPPED
import org.gradle.api.tasks.testing.logging.TestLogEvent.STANDARD_ERROR
import org.gradle.api.tasks.testing.logging.TestLogEvent.STANDARD_OUT

plugins {
	id("java-library")
	id("jvm-test-suite")
}

@Suppress("UnstableApiUsage")
testing {
	suites {
		named<JvmTestSuite>("test") {

			// Version catalog not available in precompiled script plugins:
			// https://github.com/gradle/gradle/issues/15383
			//val junitVersion = libs.version.junit
			val junitVersion = "5.9.2"

			useJUnitJupiter(junitVersion)

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

dependencies {

	// Version catalog not available in precompiled script plugins:
	// https://github.com/gradle/gradle/issues/15383

	testImplementation(platform("org.sdkotlin.platforms:test-platform"))

	//testImplementation(libs.bundles.junit)
	testImplementation("org.junit.jupiter:junit-jupiter-params")
	testImplementation("org.junit.jupiter:junit-jupiter-api")

	//testImplementation(libs.assertj.core)
	testImplementation("org.assertj:assertj-core")
}
