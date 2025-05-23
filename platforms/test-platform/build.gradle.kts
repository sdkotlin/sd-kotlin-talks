plugins {
	id("java-platform")
}

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
		api(libs.mokkery.core)
		api(libs.bundles.mockk.jvm)
	}
}
