package org.sdkotlin.tdd.fizzbuzz

import io.mockk.mockk
import io.mockk.verify
import io.mockk.verifyOrder
import org.junit.jupiter.api.Test

class FizzBuzzMockKTest {

	@Test
	fun `test fizzBuzz1To100() prints 100 FizzBuzzes with MockK Printer`() {

		val mockPrinter = mockk<Printer>()

		fizzBuzz1to100(mockPrinter)

		verify(exactly = 100) {
			mockPrinter.print(any())
		}

		verifyOrder {
			mockPrinter.print("1")
			mockPrinter.print("2")
			mockPrinter.print("Fizz")
			mockPrinter.print("Buzz")
			mockPrinter.print("FizzBuzz")
		}
	}

	@Test
	fun `test fizzBuzz1To100() prints 100 FizzBuzzes with MockK function object`() {

		val mockPrintLn = mockk<(String) -> Unit>()

		fizzBuzz1to100(mockPrintLn)

		verify(exactly = 100) {
			mockPrintLn(any())
		}

		verifyOrder {
			mockPrintLn("1")
			mockPrintLn("2")
			mockPrintLn("Fizz")
			mockPrintLn("Buzz")
			mockPrintLn("FizzBuzz")
		}
	}
}
