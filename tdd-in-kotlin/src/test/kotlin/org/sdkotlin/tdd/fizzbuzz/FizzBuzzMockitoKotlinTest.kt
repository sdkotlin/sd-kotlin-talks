package org.sdkotlin.tdd.fizzbuzz

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.inOrder
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import org.junit.jupiter.api.Test

class FizzBuzzMockitoKotlinTest {

	@Test
	fun `test fizzBuzz1To100() prints 100 FizzBuzzes with Mockito mock Printer`() {

		val mockPrinter = mock<Printer>()

		fizzBuzz1to100(mockPrinter)

		verify(mockPrinter, times(100)).print(any())

		inOrder(mockPrinter) {
			mockPrinter.print("1")
			mockPrinter.print("2")
			mockPrinter.print("Fizz")
			mockPrinter.print("Buzz")
			mockPrinter.print("FizzBuzz")
		}
	}

	@Test
	fun `test fizzBuzz1To100() prints 100 FizzBuzzes with Mockito mock function object`() {

		val mockPrinter = mock<(String) -> Unit>()

		fizzBuzz1to100(mockPrinter)

		verify(mockPrinter, times(100)).invoke(any())

		// Or...
		verify(mockPrinter, times(100))(any())

		inOrder(mockPrinter) {
			mockPrinter("1")
			mockPrinter("2")
			mockPrinter("Fizz")
			mockPrinter("Buzz")
			mockPrinter("FizzBuzz")
		}
	}
}
