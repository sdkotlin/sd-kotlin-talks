package org.sdkotlin.detekt.junit

import dev.detekt.api.Config
import dev.detekt.api.Finding
import dev.detekt.test.lintWithContext
import dev.detekt.test.utils.createEnvironment
import dev.detekt.test.utils.readResourceContent
import org.assertj.core.api.Assertions.assertThat
import org.intellij.lang.annotations.Language
import org.junit.jupiter.api.Test

internal class InvalidTestFactoryReturnTypeTest {

	private val env = createEnvironment()

	private val rule = InvalidTestFactoryReturnType(Config.empty)

	// junitFake is needed because the Analysis API requires type resolution.
	// The test environment doesn't automatically include the project's
	// dependencies on the classpath used for analyzing these snippets.
	private val junitFake =
		readResourceContent("org/sdkotlin/detekt/junit/JUnitFake.kt")

	@Test
	fun `reports Unit`() {

		@Language("kotlin")
		val code = """
			class Testcase {
				@org.junit.jupiter.api.TestFactory
				fun test() { }
			}
		"""

		val findings: List<Finding> = lint(code)

		assertThat(findings)
			.hasSize(1)
			.first()
			.extracting(Finding::message)
			.asString()
			.contains("Unit")
	}

	@Test
	fun `reports List of Unit`() {

		@Language("kotlin")
		val code = """
			class Testcase {
				@org.junit.jupiter.api.TestFactory
				fun test() = listOf(Unit)
			}
		"""

		val findings: List<Finding> = lint(code)

		assertThat(findings)
			.hasSize(1)
			.first()
			.extracting(Finding::message)
			.asString()
			.contains("List")
			.contains("Unit")
	}

	@Test
	fun `does not report single DynamicTest`() {

		@Language("kotlin")
		val code = """
			class Testcase {
				@org.junit.jupiter.api.TestFactory
				fun test() = org.junit.jupiter.api.DynamicTest.dynamicTest("testing") { }
			}
		"""

		val findings: List<Finding> = lint(code)

		assertThat(findings).isEmpty()
	}

	@Test
	fun `does not report List of DynamicTest`() {

		@Language("kotlin")
		val code = """
			class Testcase {
				@org.junit.jupiter.api.TestFactory
				fun test() =
					listOf("test 1", "test 2")
					.map { testName ->
						org.junit.jupiter.api.DynamicTest.dynamicTest(testName) { }
					}
			}
		"""

		val findings: List<Finding> = lint(code)

		assertThat(findings).isEmpty()
	}

	@Test
	fun `does not report Set of DynamicTest`() {

		@Language("kotlin")
		val code = """
			class Testcase {
				@org.junit.jupiter.api.TestFactory
				fun test() =
					listOf("test 1", "test 2")
					.map { testName ->
						org.junit.jupiter.api.DynamicTest.dynamicTest(testName) { }
					}
					.toSet()
			}
		"""

		val findings: List<Finding> = lint(code)

		assertThat(findings).isEmpty()
	}

	@Test
	fun `does not report Iterable of DynamicTest`() {

		@Language("kotlin")
		val code = """
			class Testcase {
				@org.junit.jupiter.api.TestFactory
				fun test() =
					listOf("test 1", "test 2")
					.map { testName ->
						org.junit.jupiter.api.DynamicTest.dynamicTest(testName) { }
					} as Iterable<org.junit.jupiter.api.DynamicTest>
			}
		"""

		val findings: List<Finding> = lint(code)

		assertThat(findings).isEmpty()
	}

	@Test
	fun `does not report Collection of DynamicTest`() {

		@Language("kotlin")
		val code = """
			class Testcase {
				@org.junit.jupiter.api.TestFactory
				fun test() =
					listOf("test 1", "test 2")
					.map { testName ->
						org.junit.jupiter.api.DynamicTest.dynamicTest(testName) { }
					} as Collection<org.junit.jupiter.api.DynamicTest>
			}
		"""

		val findings: List<Finding> = lint(code)

		assertThat(findings).isEmpty()
	}

	@Test
	fun `does not report Iterator of DynamicTest`() {

		@Language("kotlin")
		val code = """
			class Testcase {
				@org.junit.jupiter.api.TestFactory
				fun test() =
					listOf("test 1", "test 2")
					.map { testName ->
						org.junit.jupiter.api.DynamicTest.dynamicTest(testName) { }
					}
					.iterator()
			}
		"""

		val findings: List<Finding> = lint(code)

		assertThat(findings).isEmpty()
	}

	@Test
	fun `does not report Stream of DynamicTest`() {

		@Language("kotlin")
		val code = """
			class Testcase {
				@org.junit.jupiter.api.TestFactory
				fun test() =
					listOf("test 1", "test 2")
					.map { testName ->
						org.junit.jupiter.api.DynamicTest.dynamicTest(testName) { }
					}
					.stream()
			}
		"""

		val findings: List<Finding> = lint(code)

		assertThat(findings).isEmpty()
	}

	@Test
	fun `does not report Array of DynamicTest`() {

		@Language("kotlin")
		val code = """
			class Testcase {
				@org.junit.jupiter.api.TestFactory
				fun test() =
					listOf("test 1", "test 2")
					.map { testName ->
						org.junit.jupiter.api.DynamicTest.dynamicTest(testName) { }
					}
					.toTypedArray()
			}
		"""

		val findings: List<Finding> = lint(code)

		assertThat(findings).isEmpty()
	}

	@Test
	fun `does not report MutableList of DynamicTest`() {

		@Language("kotlin")
		val code = """
			class Testcase {
				@org.junit.jupiter.api.TestFactory
				fun test() =
					listOf("test 1", "test 2")
					.map { testName ->
						org.junit.jupiter.api.DynamicTest.dynamicTest(testName) { }
					}
					.toMutableList()
			}
		"""

		val findings: List<Finding> = lint(code)

		assertThat(findings).isEmpty()
	}

	@Test
	fun `does not report ArrayList of DynamicTest`() {

		@Language("kotlin")
		val code = """
			class Testcase {
				@org.junit.jupiter.api.TestFactory
				fun test(): java.util.ArrayList<org.junit.jupiter.api.DynamicTest> {
					return java.util.ArrayList(
						listOf("test 1", "test 2")
							.map { testName ->
								org.junit.jupiter.api.DynamicTest.dynamicTest(testName) { }
							}
					)
				}
			}
		"""

		val findings: List<Finding> = lint(code)

		assertThat(findings).isEmpty()
	}

	@Test
	fun `does not report custom Iterable subtype of DynamicTest`() {

		@Language("kotlin")
		val code = """
			class MyIterable<T>(private val it: Iterable<T>) : Iterable<T> by it

			class Testcase {
				@org.junit.jupiter.api.TestFactory
				fun test(): MyIterable<org.junit.jupiter.api.DynamicTest> {
					val tests =
						listOf("test 1", "test 2")
							.map { testName ->
								org.junit.jupiter.api.DynamicTest.dynamicTest(testName) { }
							}
					return MyIterable(tests)
				}
			}
		"""

		val findings: List<Finding> = lint(code)

		assertThat(findings).isEmpty()
	}

	@Test
	fun `does not report Sequence of DynamicTest`() {

		@Language("kotlin")
		val code = """
			class Testcase {
				@org.junit.jupiter.api.TestFactory
				fun test() =
					listOf("test 1", "test 2")
					.asSequence()
					.map { testName ->
						org.junit.jupiter.api.DynamicTest.dynamicTest(testName) { }
					}
			}
		"""

		val findings: List<Finding> = lint(code)

		assertThat(findings).isEmpty()
	}

	@Test
	fun `reports Map of DynamicTest`() {

		@Language("kotlin")
		val code = """
			class Testcase {
				@org.junit.jupiter.api.TestFactory
				fun test() = mapOf(
					"a" to org.junit.jupiter.api.DynamicTest.dynamicTest("testing") { }
				)
			}
		"""

		val findings: List<Finding> = lint(code)

		assertThat(findings)
			.hasSize(1)
			.first()
			.extracting(Finding::message)
			.asString()
			.contains("Map")
	}

	private fun lint(@Language("kotlin") code: String): List<Finding> =
		rule.lintWithContext(env, code, junitFake)
}
