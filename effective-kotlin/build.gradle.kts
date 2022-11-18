plugins {
	id("org.sdkotlin.buildlogic.kotlin-project")
}

dependencies {

	implementation(platform("org.sdkotlin.platforms:app-platform"))

	implementation(libs.org.json)
}
