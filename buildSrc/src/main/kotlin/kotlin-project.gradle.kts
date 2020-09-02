import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	java
	kotlin("jvm")
}

dependencies {
	api(kotlin("stdlib"))
	api(kotlin("stdlib-jdk7"))
	api(kotlin("stdlib-jdk8"))
	implementation(kotlin("reflect"))
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.8")
}

java {
	sourceCompatibility = JavaVersion.VERSION_11
	targetCompatibility = JavaVersion.VERSION_11
}

tasks {
	withType<KotlinCompile> {
		kotlinOptions.jvmTarget = JavaVersion.VERSION_11.toString()
	}
}
