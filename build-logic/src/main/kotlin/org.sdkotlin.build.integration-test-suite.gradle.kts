plugins {
	idea
	`jvm-test-suite`
	kotlin("jvm")
	id("org.sdkotlin.build.junit-project")
}

val integrationTestSuiteName = "integrationTest"

@Suppress("UnstableApiUsage")
testing {
	suites {

		val test by getting(JvmTestSuite::class)

		register<JvmTestSuite>(integrationTestSuiteName) {

			dependencies {

				// Version catalog not available in precompiled script plugins:
				// https://github.com/gradle/gradle/issues/15383

				implementation(project)

				implementation(
					project.dependencies.platform(
						"org.sdkotlin.platforms:test-platform"
					)
				)

				//implementation(libs.assertj.core)
				implementation("org.assertj:assertj-core")
			}

			sources {
				java {
					setSrcDirs(listOf("src/it/java"))
				}
				kotlin {
					setSrcDirs(listOf("src/it/kotlin"))
				}
			}
			targets {
				all {
					testTask.configure {
						filter {
							includeTestsMatching("*IT")
						}
						shouldRunAfter(test)
					}
				}
			}
		}
	}
}

tasks {

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
