package org.sdkotlin.equalsverifier.dataclasses

data class DataClassWithClassBodyProperty(val equalBy: String) {

	val bodyProperty: String = "Not used for equals or hashCode."
}
