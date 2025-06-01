package org.sdkotlin.integral

import io.mockk.mockk
import nl.jqno.equalsverifier.EqualsVerifier
import org.junit.jupiter.api.Test

class IntegralRangeTest {

	@Test
	fun `test equals, hashCode, and toString`() {

		EqualsVerifier.forClass(IntegralRange::class.java)
			.withIgnoredFields("endExclusive\$delegate")
			// Required per https://github.com/jqno/equalsverifier/issues/1082.
			.withPrefabValues(
				Integral::class.java,
				mockk(relaxed = true),
				mockk(relaxed = true),
			)
			.verify()
	}

	@Test
	fun `test equals, hashCode, and toString with examples`() {

		val zero = SignedIntegral(0)
		val one = SignedIntegral(1)
		val two = SignedIntegral(2)
		val red: ClosedRange<SignedIntegral> = zero..one
		val blue: ClosedRange<SignedIntegral> = one..two

		EqualsVerifier.forExamples(red, blue)
			.withIgnoredFields("endExclusive\$delegate")
			.verify()
	}
}
