plugins {
	id("java-platform")
}

group = "org.sdkotlin.platforms"

javaPlatform {
	allowDependencies()
}

dependencies {

	api(platform(libs.junit.bom))

	constraints {
		api(libs.assertj)
		api(libs.bundles.koin.test.junit5)
		api(libs.bundles.kotlintest.junit5)
		api(libs.mockito)
		api(libs.mockito.kotlin)
		api(libs.bundles.mockk.jvm)
	}
}
