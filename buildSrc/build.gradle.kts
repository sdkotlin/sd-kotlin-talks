plugins {
	`kotlin-dsl`
}

dependencies {
	val kotlinVersion = "1.6.21"
	val kspVersion = "$kotlinVersion-1.0.5"

	implementation("com.google.devtools.ksp:symbol-processing-gradle-plugin:$kspVersion")
	implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
	implementation("org.unbroken-dome.gradle-plugins:gradle-testsets-plugin:4.0.0")
}
