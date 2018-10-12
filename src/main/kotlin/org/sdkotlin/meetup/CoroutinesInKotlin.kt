package org.sdkotlin.meetup

import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.awaitAll
import kotlinx.coroutines.experimental.coroutineScope
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking
import java.lang.Thread.sleep
import kotlin.concurrent.thread
import kotlin.system.measureTimeMillis

const val ONE_THOUSAND = 1_000
const val ONE_MILLION = 1_000_000

fun main(args: Array<String>) {

	lotsOfCoroutines()

	asyncSum()

	// OutOfMemoryError!
	// (Doing this last, as it may not fail gracefully on JDK < 10.)
	lotsOfThreads()
}

private fun lotsOfCoroutines() = runBlocking {

	val elapsedTime = measureTimeMillis {
		coroutineScope {
			// Launch a lot of coroutines!
			repeat(ONE_MILLION) {
				launch {
					delay(ONE_THOUSAND.toLong())
				}
			}
		}
		// The custom scope will wait (without blocking any threads)
		// on the launched coroutines
	}

	println("Created $ONE_MILLION coroutines that sleep for $ONE_THOUSAND " +
		"milliseconds in $elapsedTime total milliseconds!")
}

private fun asyncSum() = runBlocking {

	val elapsedTime = measureTimeMillis {
		coroutineScope {
			val deferredInts = List(ONE_MILLION) { i ->
				async {
					i + 1
				}
			}

			// The custom scope won't automatically wait on all the promises
			// to be fulfilled, hence the async nature, but it will cause all
			// the scoped coroutines to cancel if one of them fails.
			deferredInts.awaitAll()
		}
	}

	println("The async initialization of a list with sequential numbers " +
		"between 1 and $ONE_MILLION took $elapsedTime milliseconds.")
}

private fun lotsOfThreads() {

	(1..ONE_MILLION).forEach {
		try {
			thread {
				sleep(ONE_THOUSAND.toLong())
			}
		} catch (e: OutOfMemoryError) {
			println("I can't do it, Captain! $it is just too many threads!")
			return
		}
	}
}
