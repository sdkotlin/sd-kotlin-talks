//package org.sdkotlin.intro.kotlin._XX_bitoperators
//
//import org.assertj.core.api.Assertions.assertThat
//import org.junit.jupiter.api.Nested
//import org.junit.jupiter.api.Test
//
//class ExtractBytesTest {
//
//	@Nested
//	inner class ExtractByteTests {
//
//		@Nested
//		inner class ExtractFirstByteTests {
//			@Test
//			fun `test extract first Byte from Int for 0`() {
//
//				val expected: Byte = 0b0000_0000
//
//				val actual = 0.extractByte(0)
//
//				assertThat(actual).isEqualTo(expected)
//			}
//
//			@Test
//			fun `test extract first Byte from Int for 1`() {
//
//				val expected: Byte = 0b0000_0001
//
//				val actual = 1.extractByte(0)
//
//				assertThat(actual).isEqualTo(expected)
//			}
//
//			@Test
//			fun `test extract first Byte from Int for 10`() {
//
//				val expected: Byte = 0b0000_1010
//
//				val actual = 10.extractByte(0)
//
//				assertThat(actual).isEqualTo(expected)
//			}
//
//			@Test
//			fun `test extract first Byte from Int for 255`() {
//
//				val expected: Byte = 0b0111_1111
//
//				val actual = 255.extractByte(0)
//
//				assertThat(actual).isEqualTo(expected)
//			}
//
//			@Test
//			fun `test extract first Byte from Int for 256`() {
//
//				val expected: Byte = 0b0000_0000
//
//				val actual = 256.extractByte(0)
//
//				assertThat(actual).isEqualTo(expected)
//			}
//		}
//
//		@Nested
//		inner class ExtractSecondByteTests {
//
//			@Test
//			fun `test extract second Byte from Int for 0000_0000_1111_1111`() {
//
//				val number = 0b0000_0000_1111_1111
//				val expected: Byte = 0b0000_0000
//
//				val actual = number.extractByte(1)
//
//				assertThat(actual).isEqualTo(expected)
//			}
//
//			@Test
//			fun `test extract second Byte from Int for 0000_0001_1111_1111`() {
//
//				val number = 0b0000_0001_1111_1111
//				val expected: Byte = 0b0000_0001
//
//				val actual = number.extractByte(1)
//
//				assertThat(actual).isEqualTo(expected)
//			}
//
//			@Test
//			fun `test extract second Byte from Int for 0000_1010_1111_1111`() {
//
//				val number = 0b0000_1010_1111_1111
//				val expected: Byte = 0b0000_1010
//
//				val actual = number.extractByte(1)
//
//				assertThat(actual).isEqualTo(expected)
//			}
//
//			@Test
//			fun `test extract second Byte from Int for 1111_1111_0000_0000`() {
//
//				val number = 0b1111_1111_0000_0000
//				val expected: Byte = 0b0111_1111
//
//				val actual = number.extractByte(1)
//
//				assertThat(actual).isEqualTo(expected)
//			}
//		}
//	}
//
//	@Nested
//	inner class ExtractBytesTests {
//
//		@Test
//		fun `test extract bytes for 0 from Int`() {
//
//			val number = 0
//
//			val actualOutput = number.toBytes()
//
//			assertThat(actualOutput).containsExactly(0b0000_0000, 0b0000_0000,
//					0b0000_0000, 0b0000_0000)
//		}
//
//		@Test
//		fun `test extract bytes for 1 from Int`() {
//
//			val number = 1
//
//			val actualOutput = number.toBytes()
//
//			assertThat(actualOutput).containsExactly(0b0000_0001, 0b0000_0000,
//					0b0000_0000, 0b0000_0000)
//		}
//	}
//}
//
//fun Int.extractByte(i: Int): Byte {
//	return ((this shr (8 * i)) and 0xFF).toByte()
//}
//
//fun Int.toBytes(): List<Byte> {
//	return listOf(
//			this.extractByte(0),
//			this.extractByte(1),
//			this.extractByte(2),
//			this.extractByte(3))
//}
