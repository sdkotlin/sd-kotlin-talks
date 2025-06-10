plugins {
	id("org.sdkotlin.buildlogic.kotlin-project")
}

dependencies {

	implementation(projects.subprojects.constantDependencies.constantProducer)
}
