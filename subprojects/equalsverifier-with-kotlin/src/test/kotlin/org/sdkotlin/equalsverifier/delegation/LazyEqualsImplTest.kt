package org.sdkotlin.equalsverifier.delegation

import nl.jqno.equalsverifier.EqualsVerifier
import org.junit.jupiter.api.Test

class LazyEqualsImplTest {

	@Test
	fun `test equals, hashCode, and toString`() {

		EqualsVerifier.forClass(LazyEqualsImpl::class.java)
			// Required per https://github.com/jqno/equalsverifier/issues/1097
			.withPrefabValues(Lazy::class.java, lazy { 1 }, lazy { 2 })
			.verify()
	}
}
