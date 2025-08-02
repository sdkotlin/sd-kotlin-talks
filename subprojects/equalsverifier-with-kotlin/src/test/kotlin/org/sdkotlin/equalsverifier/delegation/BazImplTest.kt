package org.sdkotlin.equalsverifier.delegation

import nl.jqno.equalsverifier.EqualsVerifier
import org.junit.jupiter.api.Test

class BazImplTest {

	@Test
	fun `test equals, hashCode, and toString`() {

		EqualsVerifier.forClass(BazImpl::class.java).verify()
	}
}
