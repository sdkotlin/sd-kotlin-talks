plugins {
	java
	kotlin("jvm")
}

dependencies {

	testImplementation(platform("org.sdkotlin.platforms:test-platform"))

	testImplementation("io.mockk:mockk")
	testImplementation("io.mockk:mockk-dsl-jvm")
}
