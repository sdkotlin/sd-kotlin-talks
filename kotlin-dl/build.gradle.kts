plugins {
	id("org.sdkotlin.build.kotlin-project")
}

dependencies {

	implementation(platform("org.sdkotlin.platforms:app-platform"))

	implementation(libs.bundles.kotlinx.dl)
}
