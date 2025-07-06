package org.sdkotlin.equalsverifier.delegation

import nl.jqno.equalsverifier.EqualsVerifier
import org.junit.jupiter.api.Test

class LazyEqualsImplTest {

	@Test
	fun `test equals, hashCode, and toString`() {

		EqualsVerifier
			// Required per https://github.com/jqno/equalsverifier/issues/1097
			.forExamples(LazyEqualsImpl(1, "red"), LazyEqualsImpl(2, "blue"))
			.verify()
	}
}
