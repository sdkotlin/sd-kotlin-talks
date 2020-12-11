package org.sdkotlin.intro.kotlin._XX_bitoperators

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class ExtractUBytesTest {

	@Nested
	inner class ExtractByteTests {

		@Nested
		inner class ExtractFirstByteTests {

			@Test
			fun `test extract invalid UByte from UInt for -1`() {

				assertThatIllegalArgumentException().isThrownBy {
					0.extractByte(-1)
				}
			}

			@Test
			fun `test extract invalid UByte from UInt for -4`() {

				assertThatIllegalArgumentException().isThrownBy {
					0.extractByte(4)
				}
			}

			@Test
			fun `test extract first UByte from UInt for 0`() {

				val expected: UByte = 0b0000_0000u

				val actual = 0.extractByte(0)

				assertThat(actual).isEqualTo(expected)
			}

			@Test
			fun `test extract first UByte from UInt for 1`() {

				val expected: UByte = 0b0000_0001u

				val actual = 1.extractByte(0)

				assertThat(actual).isEqualTo(expected)
			}

			@Test
			fun `test extract first UByte from UInt for 10`() {

				val expected: UByte = 0b0000_1010u

				val actual = 10.extractByte(0)

				assertThat(actual).isEqualTo(expected)
			}

			@Test
			fun `test extract first UByte from UInt for 255`() {

				val expected: UByte = 0b1111_1111u

				val actual = 255.extractByte(0)

				assertThat(actual).isEqualTo(expected)
			}

			@Test
			fun `test extract first UByte from UInt for 256`() {

				val expected: UByte = 0b0000_0000u

				val actual = 256.extractByte(0)

				assertThat(actual).isEqualTo(expected)
			}
		}

		@Nested
		inner class ExtractSecondByteTests {

			@Test
			fun `test extract second UByte from UInt for 0000_0000_1111_1111`() {

				val number = 0b0000_0000_1111_1111
				val expected: UByte = 0b0000_0000u

				val actual = number.extractByte(1)

				assertThat(actual).isEqualTo(expected)
			}

			@Test
			fun `test extract second UByte from UInt for 0000_0001_1111_1111`() {

				val number = 0b0000_0001_1111_1111
				val expected: UByte = 0b0000_0001u

				val actual = number.extractByte(1)

				assertThat(actual).isEqualTo(expected)
			}

			@Test
			fun `test extract second UByte from UInt for 0000_1010_1111_1111`() {

				val number = 0b0000_1010_1111_1111
				val expected: UByte = 0b0000_1010u

				val actual = number.extractByte(1)

				assertThat(actual).isEqualTo(expected)
			}

			@Test
			fun `test extract second UByte from UInt for 1111_1111_0000_0000`() {

				val number = 0b1111_1111_0000_0000
				val expected: UByte = 0b1111_1111u

				val actual = number.extractByte(1)

				assertThat(actual).isEqualTo(expected)
			}
		}
	}

	@Nested
	inner class ExtractBytesTests {

		@Test
		fun `test extract bytes for 0 from UInt`() {

			val actual = 0.toBytes()

			assertThat(actual).containsExactly(0b0000_0000u, 0b0000_0000u,
					0b0000_0000u, 0b0000_0000u)
		}

		@Test
		fun `test extract bytes for 1 from UInt`() {

			val actual = 1.toBytes()

			assertThat(actual).containsExactly(0b0000_0001u, 0b0000_0000u,
					0b0000_0000u, 0b0000_0000u)
		}
	}
}

fun Int.extractByte(byteIndex: Int): UByte {
	require(byteIndex in 0..3) { "byteIndex $byteIndex must be between 0-3" }
	val bitShift = byteIndex * 8
	val firstByteMask = 0xFF
	return ((this shr bitShift) and firstByteMask).toUByte()
}

fun Int.toBytes(): List<UByte> =
	(0..3).map { byteIndex -> this.extractByte(byteIndex) }

