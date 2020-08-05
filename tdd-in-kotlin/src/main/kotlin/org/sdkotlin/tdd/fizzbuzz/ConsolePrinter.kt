package org.sdkotlin.tdd.fizzbuzz

internal class ConsolePrinter : Printer {

	override fun print(fizzBuzz: String) {
		println(fizzBuzz)
	}
}
