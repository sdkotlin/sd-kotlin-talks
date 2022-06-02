plugins {
	kotlin("jvm")
	id("com.google.devtools.ksp")
	idea
}

idea {
	module {
		sourceDirs.add(file("build/generated/ksp/main/kotlin"))
		generatedSourceDirs.add(file("build/generated/ksp/main/kotlin"))
	}
}
