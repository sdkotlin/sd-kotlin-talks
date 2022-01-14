plugins {
	`kotlin-project`
	`junit-project`
	`assertj-project`
	`mockk-project`
	`mockito-kotlin-project`
}

dependencies {

	val kotlinTestVersion = "3.4.2"

	testImplementation("io.kotlintest:kotlintest-assertions:$kotlinTestVersion")
	testImplementation("io.kotlintest:kotlintest-core:$kotlinTestVersion")
	testImplementation("io.kotlintest:kotlintest-runner-junit5:$kotlinTestVersion")

	testRuntimeOnly("org.slf4j:slf4j-simple:1.7.33")
}
