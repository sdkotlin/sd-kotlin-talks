plugins {
	id("org.sdkotlin.buildlogic.kotlin-project")
	id("org.sdkotlin.buildlogic.ksp-processor-project")
	id("org.sdkotlin.buildlogic.test.unit-test-suite")
}

dependencies {

	implementation(projects.kspBuilderGenerator.api.annotations)
	implementation(projects.kspBuilderGenerator.api.builder)

	implementation(libs.bundles.kotlinpoet.ksp)
}
