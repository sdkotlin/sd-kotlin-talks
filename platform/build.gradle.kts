plugins {
	`java-platform`
}

group = "org.sdkotlin"

javaPlatform {
	allowDependencies()
}

dependencies {
	constraints {
		api("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.31")
		api("org.unbroken-dome.gradle-plugins:gradle-testsets-plugin:3.0.1")
	}
}
