import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	java
	kotlin("jvm")
}

dependencies {
	runtimeOnly(kotlin("reflect"))
}

java {
	sourceCompatibility = JavaVersion.VERSION_17
	targetCompatibility = JavaVersion.VERSION_17
}

tasks {
	withType<JavaCompile> {
		options.compilerArgs.add("--enable-preview")
	}

	withType<Test> {
		jvmArgs("--enable-preview")
	}

	withType<JavaExec> {
		jvmArgs("--enable-preview")
	}

	withType<KotlinCompile> {
		kotlinOptions {
			freeCompilerArgs = listOf("-Xjsr305=strict")
			languageVersion = "1.6"
			apiVersion = "1.6"
			jvmTarget = JavaVersion.VERSION_16.toString()
		}
	}
}
