plugins {
	`kotlin-dsl`
}

dependencies {
	implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.31")
	implementation("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:1.16.0")
	implementation("org.unbroken-dome.gradle-plugins:gradle-testsets-plugin:3.0.1")
}

kotlinDslPluginOptions {
	experimentalWarning.set(false)
}
