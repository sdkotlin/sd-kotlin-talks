package org.sdkotlin.equalsverifier.recursive

import nl.jqno.equalsverifier.EqualsVerifier
import org.junit.jupiter.api.Test

internal class DataClassWithSealedTypePropertyTest {

	@Test
	fun `test equals, hashCode, and toString`() {
		EqualsVerifier.forClass(DataClassWithSealedTypeProperty::class.java)
			// No longer required since EqualsVerifier version 4.2.6 per
			// https://github.com/jqno/equalsverifier/issues/1081.
			//.withPrefabValues(
			//	Foo::class.java,
			//	FooImpl(1),
			//	FooImpl(2),
			//)
			.verify()
	}
}
