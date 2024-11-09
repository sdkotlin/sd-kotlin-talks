plugins {
	id("java-platform")
}

javaPlatform {
	allowDependencies()
}

dependencies {

	api(platform(libs.koin.bom))
	api(platform(libs.kotlinx.coroutines.bom))

	constraints {
		api(libs.bundles.arrow.jvm)
		api(libs.jetbrains.annotations)
		api(libs.bundles.kotlinx.dl)
		api(libs.ksp.api)
		api(libs.org.json)
		api(libs.slf4j.simple)
	}
}
