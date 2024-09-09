plugins {
	id("org.sdkotlin.buildlogic.kotlin-project")
	id("org.sdkotlin.buildlogic.ksp-processed-project")
	id("org.sdkotlin.buildlogic.test.unit-test-suite")
}

dependencies {

	api(projects.subprojects.kspBuilderGenerator.api.annotations)
	api(projects.subprojects.kspBuilderGenerator.api.builder)

	ksp(projects.subprojects.kspBuilderGenerator.processor)
}
