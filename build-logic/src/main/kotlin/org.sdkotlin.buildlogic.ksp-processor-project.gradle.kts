plugins {
	kotlin("jvm")
}

dependencies {

	val versionCatalog = versionCatalogs.named("libs")

	// Version catalog type-safe accessors not available in precompiled script
	// plugins: https://github.com/gradle/gradle/issues/15383

	api(versionCatalog.findLibrary("ksp-api").get())
}
