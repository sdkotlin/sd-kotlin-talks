plugins {
	id("org.sdkotlin.buildlogic.global-exec")
	id("org.sdkotlin.buildlogic.kotlin-project")
	id("org.sdkotlin.buildlogic.test.unit-test-suite")
}

dependencies {

	api(platform("org.sdkotlin.platforms:app-platform"))

	api(libs.bundles.arrow.jvm)

	implementation(libs.bundles.kotlinx.coroutines.jvm)

	compileOnly(libs.jetbrains.annotations)

	testImplementation(platform("org.sdkotlin.platforms:test-platform"))

	testImplementation(libs.equalsverifier)
}
