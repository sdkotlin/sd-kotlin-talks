plugins {
	id("org.sdkotlin.buildlogic.kotlin-project")
	id("org.sdkotlin.buildlogic.test.unit-test-suite")
	id("org.sdkotlin.buildlogic.mokkery-project")
}

dependencies {

	testImplementation(platform("org.sdkotlin.platforms:test-platform"))

	testImplementation(libs.mokkery.core)
}
