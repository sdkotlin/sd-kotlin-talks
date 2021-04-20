// Work around https://youtrack.jetbrains.com/issue/IDEA-248947
System.setProperty("user.dir", projectDir.toString())

plugins {
	`kotlin-dsl`
}

dependencies {
	implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.32")
	implementation("org.unbroken-dome.gradle-plugins:gradle-testsets-plugin:3.0.1")
}

kotlinDslPluginOptions {
	experimentalWarning.set(false)
}
