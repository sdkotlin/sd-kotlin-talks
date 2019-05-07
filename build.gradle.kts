import org.gradle.api.tasks.wrapper.Wrapper.DistributionType

plugins {
	id("com.github.ben-manes.versions") version "0.21.0" apply false
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
	getByName<Wrapper>("wrapper") {
		gradleVersion = "5.4.1"
		distributionType = DistributionType.ALL
	}
}
