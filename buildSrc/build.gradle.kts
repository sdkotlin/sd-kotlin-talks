import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	`kotlin-dsl`
}

val javaTargetVersion = JavaVersion.VERSION_17

dependencies {
	implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.10")
	implementation("org.unbroken-dome.gradle-plugins:gradle-testsets-plugin:4.0.0")
}

java {
	sourceCompatibility = javaTargetVersion
	targetCompatibility = javaTargetVersion
}

tasks {

	withType<KotlinCompile> {
		kotlinOptions {
			jvmTarget = javaTargetVersion.toString()
		}
	}
}
