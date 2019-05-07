package org.sdkotlin.meetup

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.*

class TddInKotlinTest {

	@Test
	fun `test TDD in Kotlin for smoke`() {

		val tddInKotlin = TddInKotlin()

		assertThat(tddInKotlin.weCanDoIt()).isTrue()
	}
}
