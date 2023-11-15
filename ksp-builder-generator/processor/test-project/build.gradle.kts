plugins {
	id("org.sdkotlin.buildlogic.kotlin-project")
	id("org.sdkotlin.buildlogic.ksp-processed-project")
	id("org.sdkotlin.buildlogic.test.unit-test-suite")
}

dependencies {

	api(projects.kspBuilderGenerator.api.annotations)
	api(projects.kspBuilderGenerator.api.builder)

	ksp(projects.kspBuilderGenerator.processor)
}
