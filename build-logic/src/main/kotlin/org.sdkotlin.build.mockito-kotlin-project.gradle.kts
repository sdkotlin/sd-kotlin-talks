plugins {
	java
	kotlin("jvm")
}

dependencies {

	testImplementation(platform("org.sdkotlin.platforms:test-platform"))

	testImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin")
	testImplementation("org.mockito:mockito-core")
}
