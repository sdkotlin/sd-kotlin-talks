package org.sdkotlin.tdd.testfixtures

class InternalComponentTestDouble(
	private val testInt: Int = TEST_INT,
	private val testString: String = TEST_STRING,
) : InternalComponent {

	companion object {
		const val TEST_INT = 42
		const val TEST_STRING = "Testing"
	}

	override fun getInt(): Int = testInt

	override fun getString(): String = testString
}
