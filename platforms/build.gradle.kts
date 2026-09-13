import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask

tasks {
	// `checkConstraints` is read in the build that resolves, so it is declared
	// here as well as in the root build. The platforms' constraints are what
	// this build declares.
	named<DependencyUpdatesTask>("dependencyUpdates").configure {
		checkConstraints = true
	}
}
