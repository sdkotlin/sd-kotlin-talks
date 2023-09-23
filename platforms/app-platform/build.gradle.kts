plugins {
	id("java-platform")
}

group = "org.sdkotlin.platforms"

javaPlatform {
	allowDependencies()
}

dependencies {

	api(platform(libs.koin.bom))

	constraints {
		api(libs.bundles.arrow.jvm)
		api(libs.jetbrains.annotations)
		api(libs.bundles.koin.jvm)
		api(libs.bundles.kotlinx.coroutines.jvm)
		api(libs.bundles.kotlinx.dl)
		api(libs.org.json)
		api(libs.slf4j.simple)
	}
}
