package org.sdkotlin.detekt.junit

import io.gitlab.arturbosch.detekt.api.Config
import io.gitlab.arturbosch.detekt.api.Finding
import io.gitlab.arturbosch.detekt.rules.KotlinCoreEnvironmentTest
import io.gitlab.arturbosch.detekt.test.assertThat
import io.gitlab.arturbosch.detekt.test.lintWithContext
import org.jetbrains.kotlin.cli.jvm.compiler.KotlinCoreEnvironment
import org.junit.jupiter.api.Test

@KotlinCoreEnvironmentTest
internal class InvalidTestFactoryReturnTypeTest(
	private val env: KotlinCoreEnvironment,
) {

	private val rule = InvalidTestFactoryReturnType(Config.empty)

	@Test
	fun `reports Unit`() {

		val code = """
			class Testcase {
				@TestFactory
				fun test() { }
			}
		"""

		val findings: List<Finding> = rule.lintWithContext(env, code)

		assertThat(findings)
			.hasSize(1).first()
			.matches { it.message.contains("Found: Unit") }
	}

	@Test
	fun `reports List of Unit`() {

		val code = """
			class Testcase {
				@TestFactory
				fun test() = listOf(Unit)
			}
		"""

		val findings: List<Finding> = rule.lintWithContext(env, code)

		assertThat(findings)
			.hasSize(1).first()
			.matches { it.message.contains("Found: List<Unit>") }
	}

	@Test
	fun `does not report single DynamicTest`() {

		val code = """
			import org.junit.jupiter.api.DynamicTest.dynamicTest
			class Testcase {
				@TestFactory
				fun test() = dynamicTest("testing") { }
			}
		"""

		val findings: List<Finding> = rule.lintWithContext(env, code)

		assertThat(findings).isEmpty()
	}

	@Test
	fun `does not report List of DynamicTest`() {

		val code = """
			import org.junit.jupiter.api.DynamicTest.dynamicTest
			class Testcase {
				@TestFactory
				fun test() =
					listOf("test 1", "test 2")
					.map { testName ->
						dynamicTest(testName) { }
					}
			}
		"""

		val findings: List<Finding> = rule.lintWithContext(env, code)

		assertThat(findings).isEmpty()
	}

	@Test
	fun `does not report Set of DynamicTest`() {

		val code = """
			import org.junit.jupiter.api.DynamicTest.dynamicTest
			class Testcase {
				@TestFactory
				fun test() =
					listOf("test 1", "test 2")
					.map { testName ->
						dynamicTest(testName) { }
					}
					.toSet()
			}
		"""

		val findings: List<Finding> = rule.lintWithContext(env, code)

		assertThat(findings).isEmpty()
	}

	@Test
	fun `does not report Iterable of DynamicTest`() {

		val code = """
			import org.junit.jupiter.api.DynamicTest
			import org.junit.jupiter.api.DynamicTest.dynamicTest
			class Testcase {
				@TestFactory
				fun test() =
					listOf("test 1", "test 2")
					.map { testName ->
						dynamicTest(testName) { }
					} as Iterable<DynamicTest>
			}
		"""

		val findings: List<Finding> = rule.lintWithContext(env, code)

		assertThat(findings).isEmpty()
	}

	@Test
	fun `does not report Collection of DynamicTest`() {

		val code = """
			import org.junit.jupiter.api.DynamicTest
			import org.junit.jupiter.api.DynamicTest.dynamicTest
			class Testcase {
				@TestFactory
				fun test() =
					listOf("test 1", "test 2")
					.map { testName ->
						dynamicTest(testName) { }
					} as Collection<DynamicTest>
			}
		"""

		val findings: List<Finding> = rule.lintWithContext(env, code)

		assertThat(findings).isEmpty()
	}

	@Test
	fun `does not report Iterator of DynamicTest`() {

		val code = """
			import org.junit.jupiter.api.DynamicTest.dynamicTest
			class Testcase {
				@TestFactory
				fun test() =
					listOf("test 1", "test 2")
					.map { testName ->
						dynamicTest(testName) { }
					}
					.iterator()
			}
		"""

		val findings: List<Finding> = rule.lintWithContext(env, code)

		assertThat(findings).isEmpty()
	}

	@Test
	fun `does not report Stream of DynamicTest`() {

		val code = """
			import org.junit.jupiter.api.DynamicTest.dynamicTest
			class Testcase {
				@TestFactory
				fun test() =
					listOf("test 1", "test 2")
					.map { testName ->
						dynamicTest(testName) { }
					}
					.stream()
			}
		"""

		val findings: List<Finding> = rule.lintWithContext(env, code)

		assertThat(findings).isEmpty()
	}

	@Test
	fun `does not report Array of DynamicTest`() {

		val code = """
			import org.junit.jupiter.api.DynamicTest.dynamicTest
			class Testcase {
				@TestFactory
				fun test() =
					listOf("test 1", "test 2")
					.map { testName ->
						dynamicTest(testName) { }
					}
					.toTypedArray()
			}
		"""

		val findings: List<Finding> = rule.lintWithContext(env, code)

		assertThat(findings).isEmpty()
	}
}
