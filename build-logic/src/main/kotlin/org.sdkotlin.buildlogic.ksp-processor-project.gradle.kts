plugins {
	kotlin("jvm")
}

dependencies {

	// Version catalog type-safe accessors not available in precompiled script
	// plugins: https://github.com/gradle/gradle/issues/15383

	api(platform("org.sdkotlin.platforms:app-platform"))

	implementation("com.google.devtools.ksp:symbol-processing-api")
}
