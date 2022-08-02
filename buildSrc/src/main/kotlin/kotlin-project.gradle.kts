import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	java
	kotlin("jvm")
}

val javaTargetVersion = JavaVersion.VERSION_17
val kotlinTargetVersion = "1.7"

dependencies {
	runtimeOnly(kotlin("reflect"))
}

java {
	sourceCompatibility = javaTargetVersion
	targetCompatibility = javaTargetVersion
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
			languageVersion = kotlinTargetVersion
			apiVersion = kotlinTargetVersion
			jvmTarget = javaTargetVersion.toString()
		}
	}
}
