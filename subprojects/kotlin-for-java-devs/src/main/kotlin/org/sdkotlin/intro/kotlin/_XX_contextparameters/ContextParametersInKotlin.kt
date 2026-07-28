package org.sdkotlin.intro.kotlin._XX_contextparameters

// Some dependencies are needed by every function in a call chain without
// being part of what any of those functions is about. A logger or a
// transaction handle is the usual example.

class Logger {
	fun log(message: String) = println("LOG: $message")
}

class Transaction(val id: String)

// Threading such a dependency by hand puts it in every signature between
// the entry point and the code that uses it, even where it's only passed on.

fun placeOrderWithLogger(item: String, logger: Logger) {
	logger.log("Placing order for $item")
	reserveWithLogger(item, logger)
}

fun reserveWithLogger(item: String, logger: Logger) =
	logger.log("Reserving $item")

// A context parameter declares that dependency instead of taking it as an
// argument.

context(logger: Logger)
fun placeOrder(item: String) {

	logger.log("Placing order for $item")

	// A call that needs the same context resolves it without naming it.

	reserve(item)
}

context(logger: Logger)
fun reserve(item: String) = logger.log("Reserving $item")

// Java has no equivalent: threading the argument through every signature, a
// `ThreadLocal`, or a dependency injection framework are the closest
// options. Only threading keeps the compile-time check, and it pays for it
// in boilerplate; the other two trade it for runtime setup.

// Context parameters replace context receivers, an earlier experimental
// feature whose type-only `context(Logger)` form no longer compiles.

// A declaration can take more than one context parameter, matched by type.

context(logger: Logger, transaction: Transaction)
fun commit(item: String) =
	logger.log("Committing $item in transaction ${transaction.id}")

// Properties can take context parameters too, but only with an explicit
// getter: such a property can't have a backing field or an initializer.

context(transaction: Transaction)
val transactionLabel: String
	get() = "tx-${transaction.id}"

fun main() {

	val logger = Logger()

	placeOrderWithLogger("Widget", logger)

	// `with` makes its argument an implicit receiver, and context parameters
	// resolve against the implicit receivers in scope.

	with(logger) {
		placeOrder("Widget")

		with(Transaction("1a2b")) {
			commit("Widget")
			println(transactionLabel)
		}
	}

	// An unsatisfied context is a compile error, not a missing dependency
	// discovered at runtime.

	//placeOrder("Widget") // Does not compile.
}
