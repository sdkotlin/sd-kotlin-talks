plugins {
	id("org.sdkotlin.plugins.kotlin-project")
	id("org.sdkotlin.plugins.junit-project")
	id("org.sdkotlin.plugins.assertj-project")
	id("org.sdkotlin.plugins.mockk-project")
	id("org.sdkotlin.plugins.mockito-kotlin-project")
}

dependencies {

	testImplementation("io.kotlintest:kotlintest-runner-junit5:3.4.2")
	testRuntimeOnly("org.slf4j:slf4j-simple:1.7.30")
}
