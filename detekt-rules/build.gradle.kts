plugins {
	alias(libs.plugins.kotlin.gradlePlugin)
	id("jvm-test-suite")
}

group = "org.sdkotlin.detekt"
version = "1.0.0-SNAPSHOT"

dependencies {

	compileOnly(libs.detekt.api)

	testImplementation(libs.assertj)
	testImplementation(libs.detekt.test)
}

@Suppress("UnstableApiUsage")
testing {
	suites {
		configureEach {
			if (this is JvmTestSuite) {
				useJUnitJupiter(libs.versions.junit.get())
			}
		}
	}
}
