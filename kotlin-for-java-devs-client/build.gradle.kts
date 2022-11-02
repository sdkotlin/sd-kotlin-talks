plugins {
	id("org.sdkotlin.build.kotlin-project")
	id("org.sdkotlin.build.test.unit-test-suite")
}

dependencies {

	api(project(":kotlin-for-java-devs"))
}
