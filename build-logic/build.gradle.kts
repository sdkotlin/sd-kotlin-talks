plugins {
	`kotlin-dsl`
}

dependencies {

	implementation(platform("org.sdkotlin:platform"))

	implementation("org.jetbrains.kotlin:kotlin-gradle-plugin")
	implementation("org.unbroken-dome.gradle-plugins:gradle-testsets-plugin")
}

kotlinDslPluginOptions {
	experimentalWarning.set(false)
}
