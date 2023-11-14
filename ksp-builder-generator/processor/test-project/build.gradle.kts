plugins {
	id("org.sdkotlin.buildlogic.kotlin-project")
	id("org.sdkotlin.buildlogic.ksp-processed-project")
	id("org.sdkotlin.buildlogic.test.unit-test-suite")
}

dependencies {

	api(projects.kspBuilderGenerator.api.builder)

	implementation(projects.kspBuilderGenerator.api.annotations)

	ksp(projects.kspBuilderGenerator.processor)

	testImplementation(projects.kspBuilderGenerator.api.builder)
}
