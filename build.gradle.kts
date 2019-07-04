import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
import com.github.benmanes.gradle.versions.updates.gradle.GradleReleaseChannel.*
import org.gradle.api.tasks.wrapper.Wrapper.DistributionType

plugins {
	id("com.github.ben-manes.versions") version "0.21.0"
}

allprojects {
	group = "org.sdkotlin"
	version = "1.0-SNAPSHOT"

	repositories {
		mavenCentral()
		jcenter()
	}
}

tasks {
	named<DependencyUpdatesTask>("dependencyUpdates") {
		revision = "release"
		gradleReleaseChannel = CURRENT.id
		resolutionStrategy {
			componentSelection {
				all {
					val rejected = listOf("alpha", "beta", "rc", "cr", "m", "preview", "b", "ea").any { qualifier ->
						candidate.version.matches(Regex("(?i).*[.-]$qualifier[.\\d-+]*"))
					}
					if (rejected) {
						reject("Release candidate")
					}
				}
			}
		}
	}

	named<Wrapper>("wrapper") {
		gradleVersion = "5.5"
		distributionType = DistributionType.ALL
	}
}
