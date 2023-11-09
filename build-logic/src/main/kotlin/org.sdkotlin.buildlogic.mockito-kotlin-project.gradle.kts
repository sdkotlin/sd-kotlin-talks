plugins {
	kotlin("jvm")
}

dependencies {

	val versionCatalog = versionCatalogs.named("libs")

	testImplementation(platform("org.sdkotlin.platforms:test-platform"))

	// Version catalog type-safe accessors not available in precompiled script
	// plugins: https://github.com/gradle/gradle/issues/15383

	testImplementation(versionCatalog.findBundle("mockito").get())
}
