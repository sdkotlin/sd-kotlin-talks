package org.sdkotlin.intro.kotlin._XX_tdd

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class TddInKotlinTest {

	@Test
	fun `test TDD in Kotlin for smoke`() {

		val tddInKotlin = TddInKotlin()

		assertThat(tddInKotlin.weCanDoIt()).isTrue
	}
}
