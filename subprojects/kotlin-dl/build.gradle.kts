plugins {
	id("org.sdkotlin.buildlogic.kotlin-project")
}

dependencies {

	api(platform("org.sdkotlin.platforms:app-platform"))

	implementation(libs.kotlinx.dl.api)
	implementation(libs.kotlinx.dl.dataset)
	implementation(libs.kotlinx.dl.tensorflow)
}
