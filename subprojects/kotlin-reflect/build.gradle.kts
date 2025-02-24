plugins {
	id("org.sdkotlin.buildlogic.kotlin-project")
	id("org.sdkotlin.buildlogic.test.unit-test-suite")
}

dependencies {

	implementation(kotlin("reflect"))
}

dependencyAnalysis {
	issues {
		onRuntimeOnly {
			exclude(
				// False positive on `kotlin-reflect` implementation use:
				// https://github.com/autonomousapps/dependency-analysis-gradle-plugin/issues/1384.
				"org.jetbrains.kotlin:kotlin-reflect",
			)
		}
	}
}
