package org.sdkotlin.meetup

import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking

fun main(args: Array<String>) = runBlocking {

	val jobs = List(100_000) { // launch a lot of coroutines and list their jobs

		launch {

			delay(1000L)

			println(".")
		}
	}

	jobs.forEach { it.join() } // wait for all jobs to complete
}
