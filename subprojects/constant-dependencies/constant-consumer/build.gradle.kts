plugins {
	id("org.sdkotlin.buildlogic.kotlin-project")
}

dependencies {

	implementation(projects.subprojects.constantDependencies.constantProducer)
}

dependencyAnalysis {
	issues {
		onUnusedDependencies {
			exclude(
				// Used but reported as unused:
				// https://github.com/autonomousapps/dependency-analysis-gradle-plugin/issues/1385.
				":subprojects:constant-dependencies:constant-producer",
			)
		}
	}
}
