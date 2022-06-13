dependencyResolutionManagement {
	@Suppress("UnstableApiUsage")
	versionCatalogs {
		create("libs") {
			from(files("../gradle/libs.versions.toml"))
		}
	}
}

rootProject.name = "platforms"

include("app-platform")
include("test-platform")
include("plugins-platform")
