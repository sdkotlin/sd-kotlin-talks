import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	java
	kotlin("jvm")
}

dependencies {
	implementation(kotlin("reflect"))
}

java {
	sourceCompatibility = JavaVersion.VERSION_11
	targetCompatibility = JavaVersion.VERSION_11
}

tasks {
	withType<KotlinCompile> {
		with(kotlinOptions) {
			languageVersion = "1.4"
			apiVersion = "1.4"
			useIR = true
			jvmTarget = JavaVersion.VERSION_11.toString()
		}
	}
}
