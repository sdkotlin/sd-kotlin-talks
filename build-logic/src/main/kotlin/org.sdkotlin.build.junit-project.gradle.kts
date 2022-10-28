import org.gradle.api.tasks.testing.logging.TestLogEvent.FAILED
import org.gradle.api.tasks.testing.logging.TestLogEvent.PASSED
import org.gradle.api.tasks.testing.logging.TestLogEvent.SKIPPED
import org.gradle.api.tasks.testing.logging.TestLogEvent.STANDARD_ERROR
import org.gradle.api.tasks.testing.logging.TestLogEvent.STANDARD_OUT

plugins {
	java
}

dependencies {

	testImplementation(platform("org.sdkotlin.platforms:test-platform"))

	// Version catalog not available in precompiled script plugins:
	// https://github.com/gradle/gradle/issues/15383

	testImplementation("org.junit.jupiter:junit-jupiter-api")
	testImplementation("org.junit.jupiter:junit-jupiter-params")

	testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

tasks {

	withType<Test>().configureEach {
		useJUnitPlatform()
		testLogging {
			showStandardStreams = true
			events(PASSED, SKIPPED, FAILED, STANDARD_OUT, STANDARD_ERROR)
		}
	}
}
