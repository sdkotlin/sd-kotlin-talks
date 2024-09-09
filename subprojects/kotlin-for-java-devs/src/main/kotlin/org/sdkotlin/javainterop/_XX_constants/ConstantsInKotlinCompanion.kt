package org.sdkotlin.javainterop._XX_constants

class ConstantsInKotlinCompanion {

	companion object {

		@JvmField
		val COMPANION_JVM_FIELD_VAL = "Testing @JvmField val in object"

		@JvmStatic
		val COMPANION_JVM_STATIC_VAL = "Testing @JvmStatic val in object"

		// '@JvmStatic' annotation is useless for const or '@JvmField' properties

		//@JvmStatic
		//const val COMPANION_JVM_STATIC_CONST_VAL = "Testing @JvmStatic const val in object"
	}
}
