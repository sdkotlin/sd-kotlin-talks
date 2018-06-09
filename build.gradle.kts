import org.gradle.api.tasks.wrapper.Wrapper.*
import org.jetbrains.kotlin.gradle.dsl.Coroutines
import org.jetbrains.kotlin.gradle.tasks.*

plugins {
	kotlin("jvm") version "1.2.41"
}

group = "org.sdkotlin"
version = "1.0-SNAPSHOT"

repositories {
	mavenCentral()
}

dependencies {
	compile(kotlin("stdlib-jdk8"))
	compile("org.jetbrains.kotlinx:kotlinx-coroutines-core:0.22.5")
}

java {
	sourceCompatibility = JavaVersion.VERSION_1_8
	targetCompatibility = JavaVersion.VERSION_1_8
}

kotlin.experimental.coroutines = Coroutines.ENABLE

tasks.withType<KotlinCompile> {
	kotlinOptions.jvmTarget = "1.8"
}

task<Wrapper>("wrapper") {
	gradleVersion = "4.8"
	distributionType = DistributionType.ALL
}
