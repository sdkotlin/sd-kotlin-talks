plugins {
	kotlin("jvm")
	id("jvm-test-suite")
	id("org.sdkotlin.buildlogic.test.unit-test-suite")
}

@Suppress("UnstableApiUsage")
testing {
	suites {

		val test by getting(JvmTestSuite::class)

		register<JvmTestSuite>("integrationTest") {

			dependencies {
				implementation(project())
			}

			sources {
				val sourcesRootDir = "src/it"
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
							val testSuffix = "IT"
							includeTestsMatching("*$testSuffix")
							// Support JUnit @Nested tests
							includeTestsMatching("*$testSuffix$*")
						}
						shouldRunAfter(test)
					}
				}
			}
		}
	}
}

dependencies {

	// Version catalog not available in precompiled script plugins:
	// https://github.com/gradle/gradle/issues/15383

	"integrationTestImplementation"(
		platform("org.sdkotlin.platforms:test-platform")
	)

	"integrationTestApi"("org.junit.jupiter:junit-jupiter-api")

	"integrationTestImplementation"("org.assertj:assertj-core")
}

tasks {

	named<Task>("check").configure {
		val integrationTest by existing
		dependsOn(integrationTest)
	}
}
