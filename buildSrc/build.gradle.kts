plugins {
	`kotlin-dsl`
}

repositories {
	jcenter()
}

dependencies {
	implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.3.41")
}

kotlinDslPluginOptions {
	experimentalWarning.set(false)
}
