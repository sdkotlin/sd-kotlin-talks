plugins {
	`kotlin-project`
}

dependencies {
	val kotlinDlVersion = "0.3.0"

	implementation("org.jetbrains.kotlinx:kotlin-deeplearning-api:$kotlinDlVersion")
	implementation("org.jetbrains.kotlinx:kotlin-deeplearning-dataset:$kotlinDlVersion")
}
