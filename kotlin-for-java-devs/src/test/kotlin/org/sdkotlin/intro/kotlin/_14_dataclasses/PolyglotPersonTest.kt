package org.sdkotlin.intro.kotlin._14_dataclasses

import nl.jqno.equalsverifier.EqualsVerifier
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class PolyglotPersonTest {

	@Test
	fun `test equals, hashCode, and toString`() {
		EqualsVerifier.forClass(PolyglotPerson::class.java).verify()
	}

	@Test
	fun `test data class deep equals for immutable lists`() {
		val kotlinJavaPerson = PolyglotPerson(
			"Fran", listOf(
				ProgrammingLanguage("Kotlin"), ProgrammingLanguage("Java")
			)
		)

		val javaKotlinPerson = PolyglotPerson(
			"Fran", listOf(
				ProgrammingLanguage("Java"), ProgrammingLanguage("Kotlin")
			)
		)

		assertThat(kotlinJavaPerson).isNotEqualTo(javaKotlinPerson)
	}
}
