plugins {
	java
}

dependencies {

	testImplementation(platform("org.sdkotlin.platforms:test-platform"))

	// https://github.com/gradle/gradle/issues/15383
	//testImplementation(libs.assertj.core)
	testImplementation("org.assertj:assertj-core")
}
