plugins {
	id("org.sdkotlin.build.kotlin-project")
}

dependencies {

	api(platform("org.sdkotlin.platforms:app-platform"))

	api(libs.kotlinx.dl.api)

	implementation(libs.kotlinx.dl.dataset)
}
