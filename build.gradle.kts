import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
import com.github.benmanes.gradle.versions.updates.gradle.GradleReleaseChannel.CURRENT
import org.gradle.api.tasks.wrapper.Wrapper.DistributionType.ALL

plugins {
	id("com.autonomousapps.dependency-analysis") version "0.71.0"
	id("com.github.ben-manes.versions") version "0.36.0"
}

allprojects {
	group = "org.sdkotlin"
	version = "1.0-SNAPSHOT"

	repositories {
		mavenCentral()
		jcenter()
	}
}

subprojects {
	tasks {
		register<DependencyReportTask>("allDependencies") {
			description = "Display dependencies report for all subprojects."
			group = "help"
		}
	}
}

dependencyAnalysis {
	issues {
		all {
			onAny {
				severity("fail")
			}
		}
	}
}

tasks {
	withType<DependencyUpdatesTask> {
		rejectVersionIf {
			isNonStable(candidate.version)
		}
		gradleReleaseChannel = CURRENT.id
	}

	named<Wrapper>("wrapper") {
		gradleVersion = "6.8.2"
		distributionType = ALL
	}
}

fun isNonStable(version: String): Boolean {
	val stableKeyword = listOf("RELEASE", "FINAL", "GA").any {
		version.toUpperCase()
				.contains(it)
	}
	val unstableKeyword =
		listOf("""M\d+""").any { version.toUpperCase().contains(it.toRegex()) }
	val regex = "^[0-9,.v-]+(-r)?$".toRegex()
	val isStable = (stableKeyword && !unstableKeyword) || regex.matches(version)
	return isStable.not()
}
