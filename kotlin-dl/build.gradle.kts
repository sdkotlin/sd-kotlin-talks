plugins {
	id("org.sdkotlin.plugins.kotlin-project")
}

repositories {
	jcenter()
	maven("https://kotlin.bintray.com/kotlin-datascience")
}

dependencies {
	implementation("org.jetbrains.kotlin-deeplearning:api:0.1.1")
}
