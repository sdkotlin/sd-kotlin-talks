package org.junit.jupiter.api

annotation class TestFactory

abstract class DynamicNode

class DynamicTest : DynamicNode() {
	companion object {
		@JvmStatic
		fun dynamicTest(
			displayName: String,
			executable: () -> Unit,
		): DynamicTest = DynamicTest()
	}
}
