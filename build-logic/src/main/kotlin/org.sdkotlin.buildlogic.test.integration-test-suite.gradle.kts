plugins {
	kotlin("jvm")
	id("jvm-test-suite")
	id("org.sdkotlin.buildlogic.test.unit-test-suite")
}

val testSuiteName = "integrationTest"
val testSuiteDirName = "it"
val testSuiteTestSuffix = "IT"

@Suppress("UnstableApiUsage")
testing {
	suites {

		val test by getting(JvmTestSuite::class)

		register<JvmTestSuite>(testSuiteName) {

			dependencies {
				implementation(project())
			}

			sources {
				val sourcesRootDir = "src/$testSuiteDirName"
				java {
					setSrcDirs(listOf("$sourcesRootDir/java"))
				}
				kotlin {
					setSrcDirs(
						listOf(
							"$sourcesRootDir/kotlin",
							"$sourcesRootDir/java",
						)
					)
				}
				resources {
					setSrcDirs(listOf("$sourcesRootDir/resources"))
				}
			}

			targets {
				all {
					testTask.configure {
						filter {
							includeTestsMatching("*$testSuiteTestSuffix")
							// Support JUnit @Nested tests
							includeTestsMatching("*$testSuiteTestSuffix$*")
						}
						shouldRunAfter(test)
					}
				}
			}
		}
	}
}

dependencies {

	// Version catalog type-safe accessors not available in precompiled script
	// plugins: https://github.com/gradle/gradle/issues/15383

	"integrationTestImplementation"(
		platform("org.sdkotlin.platforms:test-platform")
	)

	"integrationTestImplementation"("org.junit.jupiter:junit-jupiter-api")
	"integrationTestImplementation"("org.assertj:assertj-core")
}

tasks {

	named<Task>("check").configure {
		val integrationTest by existing
		dependsOn(integrationTest)
	}
}
