import org.gradle.api.tasks.testing.logging.TestLogEvent.FAILED
import org.gradle.api.tasks.testing.logging.TestLogEvent.PASSED
import org.gradle.api.tasks.testing.logging.TestLogEvent.SKIPPED
import org.gradle.api.tasks.testing.logging.TestLogEvent.STANDARD_ERROR
import org.gradle.api.tasks.testing.logging.TestLogEvent.STANDARD_OUT

plugins {
	id("java-library")
	id("jvm-test-suite")
}

val versionCatalog = versionCatalogs.named("libs")

@Suppress("UnstableApiUsage")
testing {
	suites {
		configureEach {
			if (this is JvmTestSuite) {

				// Version catalog type-safe accessors not available in
				// precompiled script plugins:
				// https://github.com/gradle/gradle/issues/15383
				val junitVersion = versionCatalog.findVersion("junit")
					.get().preferredVersion

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
}

dependencies {

	// Version catalog type-safe accessors not available in precompiled script
	// plugins: https://github.com/gradle/gradle/issues/15383

	testImplementation(platform("org.sdkotlin.platforms:test-platform"))

	testImplementation(versionCatalog.findLibrary("assertj").get())
	testImplementation(versionCatalog.findLibrary("junit-api").get())
	testImplementation(versionCatalog.findLibrary("junit-params").get())
}
