plugins {
	id("org.sdkotlin.buildlogic.global-exec")
	id("org.sdkotlin.buildlogic.kotlin-project")
	id("org.sdkotlin.buildlogic.test.unit-test-suite")
}

dependencies {

	api(projects.subprojects.kotlinForJavaDevs)
}
