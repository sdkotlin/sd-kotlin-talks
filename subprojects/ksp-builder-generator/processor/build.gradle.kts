plugins {
	id("org.sdkotlin.buildlogic.kotlin-project")
	id("org.sdkotlin.buildlogic.ksp-processor-project")
	id("org.sdkotlin.buildlogic.test.unit-test-suite")
}

dependencies {

	implementation(projects.subprojects.kspBuilderGenerator.api.annotations)
	implementation(projects.subprojects.kspBuilderGenerator.api.builder)

	implementation(libs.bundles.kotlinpoet.ksp)
}
