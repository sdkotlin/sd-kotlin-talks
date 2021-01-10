import org.gradle.api.tasks.testing.logging.TestLogEvent.FAILED
import org.gradle.api.tasks.testing.logging.TestLogEvent.PASSED
import org.gradle.api.tasks.testing.logging.TestLogEvent.SKIPPED
import org.gradle.api.tasks.testing.logging.TestLogEvent.STANDARD_ERROR
import org.gradle.api.tasks.testing.logging.TestLogEvent.STANDARD_OUT

plugins {
	idea
	kotlin("jvm")
	id("org.unbroken-dome.test-sets")
}

val integrationTestSuiteName = "integrationTest"

testSets {
	create(integrationTestSuiteName) {
		dirName = "it"
	}
}

tasks {

	withType<Test> {
		useJUnitPlatform()
		testLogging {
			showStandardStreams = true
			events(PASSED, SKIPPED, FAILED, STANDARD_OUT, STANDARD_ERROR)
		}
	}

	named<Test>(integrationTestSuiteName) {
		filter {
			includeTestsMatching("*IT")
		}
		val test by existing
		shouldRunAfter(test)
	}

	named<Task>("check") {
		val integrationTest by existing
		dependsOn(integrationTest)
	}
}

idea {
	module {
		testSourceDirs.addAll(
				kotlin.sourceSets[integrationTestSuiteName].kotlin.srcDirs)
		testResourceDirs.addAll(
				kotlin.sourceSets[integrationTestSuiteName].resources.srcDirs)
	}
}
