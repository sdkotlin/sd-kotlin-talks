import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
import org.gradle.api.tasks.wrapper.Wrapper.DistributionType.ALL

plugins {
	id("com.github.ben-manes.versions") version "0.28.0"
}

allprojects {
	group = "org.sdkotlin"
	version = "1.0-SNAPSHOT"

	repositories {
		mavenCentral()
		jcenter()
	}
}

fun isNonStable(version: String): Boolean {
	val stableKeyword = listOf("RELEASE", "FINAL", "GA").any { version.toUpperCase().contains(it) }
	val regex = "^[0-9,.v-]+(-r)?$".toRegex()
	val isStable = stableKeyword || regex.matches(version)
	return isStable.not()
}

tasks {
	withType<DependencyUpdatesTask> {
		rejectVersionIf {
			isNonStable(candidate.version)
		}
	}

	named<Wrapper>("wrapper") {
		gradleVersion = "6.2.2"
		distributionType = ALL
	}
}
