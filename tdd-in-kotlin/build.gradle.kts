plugins {
	`kotlin-project`
	`junit-project`
	`assertj-project`
}

dependencies {

	testImplementation("io.kotlintest:kotlintest-runner-junit5:3.4.2")
	testRuntimeOnly("org.slf4j:slf4j-simple:1.7.29")
}
