import org.jetbrains.kotlin.gradle.plugin.KotlinCompilation.Companion.MAIN_COMPILATION_NAME
import org.jetbrains.kotlin.gradle.plugin.KotlinCompilation.Companion.TEST_COMPILATION_NAME
import org.sdkotlin.buildlogic.test.Compilations.TEST_FIXTURES_COMPILATION_NAME

plugins {
	kotlin("jvm")
	id("java-test-fixtures")
}

kotlin {
	target {
		// Workaround for https://youtrack.jetbrains.com/issue/KTIJ-23114.
		compilations.getByName(TEST_FIXTURES_COMPILATION_NAME)
			.associateWith(compilations.getByName(MAIN_COMPILATION_NAME))
		compilations.getByName(TEST_COMPILATION_NAME)
			.associateWith(compilations.getByName(TEST_FIXTURES_COMPILATION_NAME))
	}
}
