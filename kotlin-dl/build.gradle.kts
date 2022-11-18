plugins {
	id("org.sdkotlin.buildlogic.kotlin-project")
}

dependencies {

	api(platform("org.sdkotlin.platforms:app-platform"))

	api(libs.kotlinx.dl.api)

	implementation(libs.kotlinx.dl.dataset)
}
