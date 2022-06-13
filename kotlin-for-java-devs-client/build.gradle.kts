plugins {
	id("org.sdkotlin.build.kotlin-project")
	id("org.sdkotlin.build.junit-project")
	id("org.sdkotlin.build.assertj-project")
}

dependencies {

	api(project(":kotlin-for-java-devs"))
}
