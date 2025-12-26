plugins {
	kotlin("jvm")
	// Version catalog type-safe accessors and extension not yet available in
	// plugins block of precompiled script plugins:
	// https://github.com/gradle/gradle/issues/15383
	// alias(libs.plugins.detekt.gradle.plugin)
	id("dev.detekt")
}

dependencies {
	detektPlugins("org.sdkotlin.detekt:detekt-rules:1.0.1-SNAPSHOT")
}

tasks {
	named<Task>("check").configure {
		this.setDependsOn(this.dependsOn.filterNot {
			it is TaskProvider<*> && it.name == "detekt"
		})

		val detektMain by existing
		dependsOn(detektMain)

		val detektTest by existing
		dependsOn(detektTest)
	}
}
