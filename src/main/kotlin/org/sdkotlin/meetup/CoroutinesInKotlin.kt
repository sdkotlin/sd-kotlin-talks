package org.sdkotlin.meetup

import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking
import java.lang.System.gc
import java.util.concurrent.atomic.AtomicInteger
import kotlin.concurrent.thread

fun main(args: Array<String>) {

	//lotsOfThreads() // OutOfMemoryError!

	lotsOfCoroutines()

	asyncStyle()
}

private fun lotsOfThreads() {

	(1..100_000).forEach {

		try {
			thread {
				Thread.sleep(1000)
			}

		} catch (e: OutOfMemoryError) {
			println("I can't do it, Captain!  ${it} is just too many threads!")
			return
		}
	}
}

private fun lotsOfCoroutines() {

	runBlocking {

		// Launch a lot of coroutines!
		val jobs = List(1_000_000) {

			launch {
				delay(1000)
			}
		}

		jobs.forEach { it.join() } // wait for all jobs to complete

		println("No trouble creating ${jobs.size} coroutines!")
	}
}

private fun asyncStyle() {

	runBlocking {

		val deferredInts = List(1_000_000) { i ->
			async {
				i + 1
			}
		}

		val sum = deferredInts.sumBy { deferred -> deferred.await() }

		println("The sum of all numbers between 1 and ${deferredInts.size} is $sum.")
	}
}
