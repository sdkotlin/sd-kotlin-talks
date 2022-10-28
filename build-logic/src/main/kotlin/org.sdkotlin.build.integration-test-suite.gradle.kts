plugins {
	idea
	kotlin("jvm")
	// Version catalog not available in precompiled script plugins:
	// https://github.com/gradle/gradle/issues/15383
	// alias(libs.plugins.testsets.gradle.plugin)
	id("org.unbroken-dome.test-sets")
}

val integrationTestSuiteName = "integrationTest"

testSets {
	create(integrationTestSuiteName) {
		dirName = "it"
	}
}

tasks {

	named<Test>(integrationTestSuiteName).configure {
		filter {
			includeTestsMatching("*IT")
		}
		val test by existing
		shouldRunAfter(test)
	}

	named<Task>("check").configure {
		val integrationTest by existing
		dependsOn(integrationTest)
	}
}

idea {
	module {
		testSourceDirs.addAll(
			kotlin.sourceSets[integrationTestSuiteName].kotlin.srcDirs
		)
		testResourceDirs.addAll(
			kotlin.sourceSets[integrationTestSuiteName].resources.srcDirs
		)
	}
}
