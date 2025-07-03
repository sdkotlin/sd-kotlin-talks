package org.sdkotlin.equalsverifier.delegation

import nl.jqno.equalsverifier.EqualsVerifier
import org.junit.jupiter.api.Test

class BarImplTest {

	@Test
	fun `test equals, hashCode, and toString`() {

		EqualsVerifier.forClass(BarImpl::class.java).verify()
	}
}
