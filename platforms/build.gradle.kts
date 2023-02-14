plugins {
	id("java")
}

java {
	toolchain {
		languageVersion.set(
			JavaLanguageVersion.of(
				JavaVersion.VERSION_17.toString()
			)
		)
	}
}
