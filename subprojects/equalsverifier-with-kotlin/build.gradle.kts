plugins {
	id("org.sdkotlin.buildlogic.kotlin-project")
	id("org.sdkotlin.buildlogic.test.unit-test-suite")
}

dependencies {

	testImplementation(platform("org.sdkotlin.platforms:test-platform"))

	testImplementation(libs.equalsverifier)

	testRuntimeOnly(libs.mockito) {
		because("Used by EqualsVerifier for prefab value generation.")
	}
	testRuntimeOnly(kotlin("reflect"))
}
