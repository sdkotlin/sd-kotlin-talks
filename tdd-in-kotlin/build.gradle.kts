plugins {
	id("org.sdkotlin.build.kotlin-project")
	id("org.sdkotlin.build.test.unit-test-suite")
	id("org.sdkotlin.build.mockk-project")
	id("org.sdkotlin.build.mockito-kotlin-project")
}

dependencies {

	testImplementation(platform("org.sdkotlin.platforms:test-platform"))

	testImplementation(libs.bundles.kotlintest.junit5)

	testRuntimeOnly(platform("org.sdkotlin.platforms:app-platform"))

	testRuntimeOnly(libs.slf4j.simple)
}
