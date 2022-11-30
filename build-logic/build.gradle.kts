plugins {
	`kotlin-dsl`
	`java-gradle-plugin`
}

gradlePlugin {
	plugins {
		create("greetingPlugin") {
			id = "org.sdkotlin.buildlogic.greeting"
			implementationClass = "org.sdkotlin.buildlogic.GreetingPlugin"
		}
	}
}

dependencies {

	implementation(platform("org.sdkotlin.platforms:plugins-platform"))

	implementation(libs.dependency.analysis.gradle.plugin.dependency)
	implementation(libs.kotlin.gradle.plugin.dependency)
}
