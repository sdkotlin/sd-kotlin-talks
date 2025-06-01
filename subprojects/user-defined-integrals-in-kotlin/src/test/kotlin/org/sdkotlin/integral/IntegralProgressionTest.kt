package org.sdkotlin.integral

import io.mockk.mockk
import nl.jqno.equalsverifier.EqualsVerifier
import org.junit.jupiter.api.Test

class IntegralProgressionTest {

	@Test
	fun `test equals, hashCode, and toString`() {
		EqualsVerifier.forClass(IntegralProgression::class.java)
			// Required per https://github.com/jqno/equalsverifier/issues/1082.
			.withPrefabValues(
				Integral::class.java,
				mockk(relaxed = true),
				mockk(relaxed = true),
			)
			.verify()
	}
}
