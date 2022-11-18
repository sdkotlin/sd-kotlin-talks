plugins {
	id("org.sdkotlin.buildlogic.kotlin-project")
	id("org.sdkotlin.buildlogic.test.unit-test-suite")
	id("org.sdkotlin.buildlogic.mockk-project")
	id("org.sdkotlin.buildlogic.mockito-kotlin-project")
}

dependencies {

	testImplementation(platform("org.sdkotlin.platforms:test-platform"))

	testImplementation(libs.bundles.kotlintest.junit5)

	testRuntimeOnly(platform("org.sdkotlin.platforms:app-platform"))

	testRuntimeOnly(libs.slf4j.simple)
}
