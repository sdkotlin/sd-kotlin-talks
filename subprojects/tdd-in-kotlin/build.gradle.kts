plugins {
	id("org.sdkotlin.buildlogic.global-exec")
	id("org.sdkotlin.buildlogic.kotlin-project")
	id("org.sdkotlin.buildlogic.test.test-fixtures-project")
	id("org.sdkotlin.buildlogic.test.unit-test-suite")
	id("org.sdkotlin.buildlogic.mockk-project")
	id("org.sdkotlin.buildlogic.mockito-kotlin-project")
}

dependencies {

	testImplementation(platform("org.sdkotlin.platforms:test-platform"))

	testImplementation(testFixtures(projects.subprojects.tddInKotlin))

	testImplementation(libs.kotest.assertions.core.jvm)
	testImplementation(libs.kotest.assertions.table.jvm)
	testImplementation(libs.kotest.framework.engine)
	testImplementation(libs.kotest.runner.junit5)

	testRuntimeOnly(platform("org.sdkotlin.platforms:app-platform"))

	testRuntimeOnly(libs.slf4j.simple)
}
