plugins {
	id("org.sdkotlin.buildlogic.kotlin-project")
	id("org.sdkotlin.buildlogic.test.test-fixtures-project")
	id("org.sdkotlin.buildlogic.test.unit-test-suite")
	id("org.sdkotlin.buildlogic.mockk-project")
	id("org.sdkotlin.buildlogic.mockito-kotlin-project")
}

dependencies {

	testImplementation(platform("org.sdkotlin.platforms:test-platform"))

	testImplementation(testFixtures(projects.tddInKotlin))

	testImplementation(libs.kotest.assertions.shared)
	testImplementation(libs.kotest.framework.api.jvm)
	testImplementation(libs.bundles.mockk.jvm)

	testRuntimeOnly(platform("org.sdkotlin.platforms:app-platform"))

	testRuntimeOnly(libs.kotest.runner.junit5.jvm)
	testRuntimeOnly(libs.slf4j.simple)
}
