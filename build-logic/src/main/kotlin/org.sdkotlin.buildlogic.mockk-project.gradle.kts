plugins {
	kotlin("jvm")
}

dependencies {

	testImplementation(platform("org.sdkotlin.platforms:test-platform"))

	// Version catalog not available in precompiled script plugins:
	// https://github.com/gradle/gradle/issues/15383

	testImplementation("io.mockk:mockk")
	testImplementation("io.mockk:mockk-dsl-jvm")
}
