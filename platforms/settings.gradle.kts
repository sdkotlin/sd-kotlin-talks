dependencyResolutionManagement {
	versionCatalogs {
		create("libs") {
			from(files("../gradle/libs.versions.toml"))
		}
	}
}

rootProject.name = "platforms"

gradle.beforeProject {
	// Set group and version properties for all projects
	group = "org.sdkotlin.platforms"
	version = "1.0.0-SNAPSHOT"
}

include("app-platform")
include("test-platform")
include("plugins-platform")
