package org.sdkotlin.meetup

import kotlinx.coroutines.experimental.*
import kotlin.concurrent.*
import kotlin.system.*

const val ONE_THOUSAND = 1_000
const val ONE_MILLION = 1_000_000

fun main(args: Array<String>) {

	//lotsOfThreads() // OutOfMemoryError!

	lotsOfCoroutines()

	asyncSum()
}

private fun lotsOfThreads() {

	(1..ONE_MILLION).forEach {

		try {
			thread {
				Thread.sleep(ONE_THOUSAND.toLong())
			}
		} catch (e: OutOfMemoryError) {
			println("I can't do it, Captain!  $it is just too many threads!")
			return
		}
	}
}

private fun lotsOfCoroutines() {

	val elapsedTime = measureTimeMillis {
		// Launch a lot of coroutines!
		val jobs = List(ONE_MILLION) {

			GlobalScope.launch {
				delay(ONE_THOUSAND)
			}
		}

		runBlocking {

			jobs.forEach { it.join() } // wait for all jobs to complete
		}

	}

	println(
		"Created $ONE_MILLION coroutines that sleep for $ONE_THOUSAND milliseconds in $elapsedTime total milliseconds!")
}

private fun asyncSum() {

	val elapsedTime = measureTimeMillis {

		val deferredInts = List(ONE_MILLION) { i ->
			GlobalScope.async {
				i + 1
			}
		}

		runBlocking {
			deferredInts.awaitAll()
		}
	}

	println("The async initialization of a list with sequential numbers between 1 and $ONE_MILLION " +
		"took $elapsedTime milliseconds.")
}
