import org.gradle.api.JavaVersion.VERSION_1_8

plugins {
	java
}

java {
	sourceCompatibility = VERSION_1_8
	targetCompatibility = VERSION_1_8
}
