import org.jetbrains.kotlin.gradle.plugin.KotlinCompilation.Companion.MAIN_COMPILATION_NAME

plugins {
	kotlin("jvm")
	id("java-test-fixtures")
}

kotlin {
	target {
		// Workaround for https://youtrack.jetbrains.com/issue/KTIJ-23114.
		compilations.getByName("testFixtures")
			.associateWith(compilations.getByName(MAIN_COMPILATION_NAME))
	}
}
