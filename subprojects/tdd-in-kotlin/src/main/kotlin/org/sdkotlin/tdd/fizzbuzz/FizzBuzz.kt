package org.sdkotlin.tdd.fizzbuzz

fun main() {

	println("Function-oriented...")
	fizzBuzz1to100(::println)

	println("Object-oriented...")
	fizzBuzz1to100(ConsolePrinter())
}

internal fun fizzBuzz1to100(printFunction: (String) -> Unit) {
	(1..100).forEach { printFunction(fizzBuzzOf(it)) }
}

internal fun fizzBuzz1to100(printer: Printer) {
	fizzBuzz1to100 { fizzBuzz -> printer.print(fizzBuzz) }
}

internal fun fizzBuzzOf(n: Int): String {
	if (n % 15 == 0) return "FizzBuzz"
	if (n % 5 == 0) return "Buzz"
	if (n % 3 == 0) return "Fizz"
	return n.toString()
}
