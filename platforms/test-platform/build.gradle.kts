plugins {
	id("java-platform")
}

group = "org.sdkotlin.platforms"

javaPlatform {
	allowDependencies()
}

dependencies {

	api(platform(libs.junit.bom))
	api(platform(libs.koin.bom))
	api(platform(libs.kotest.bom))

	constraints {
		api(libs.assertj)
		api(libs.equalsverifier)
		api(libs.mockito)
		api(libs.mockito.kotlin)
		api(libs.bundles.mockk.jvm)
	}
}
