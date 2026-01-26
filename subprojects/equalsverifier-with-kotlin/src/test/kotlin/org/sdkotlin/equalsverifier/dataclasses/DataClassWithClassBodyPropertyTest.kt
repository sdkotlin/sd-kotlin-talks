package org.sdkotlin.equalsverifier.dataclasses

import nl.jqno.equalsverifier.EqualsVerifier
import org.junit.jupiter.api.Test

class DataClassWithClassBodyPropertyTest {

	@Test
	fun `test equals and hashCode`() {

		EqualsVerifier.forClass(DataClassWithClassBodyProperty::class.java)
			// Not needed with EqualsVerifier 4.3.1+ per
			// https://github.com/jqno/equalsverifier/pull/1159.
			//.withIgnoredFields(DataClassWithClassBodyProperty::bodyProperty.name)
			.verify()
	}
}
