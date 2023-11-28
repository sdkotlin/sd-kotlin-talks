package org.sdkotlin.tdd.testfixtures

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.sdkotlin.tdd.testfixtures.InternalComponentTestDouble.Companion.TEST_INT
import org.sdkotlin.tdd.testfixtures.InternalComponentTestDouble.Companion.TEST_STRING

internal class InternalComponentTest {

	@Test
	fun testInternalComponent() {

		val internalComponent: InternalComponent = InternalComponentTestDouble()

		assertThat(internalComponent.getInt()).isEqualTo(TEST_INT)

		assertThat(internalComponent.getString()).isEqualTo(TEST_STRING)
	}
}
