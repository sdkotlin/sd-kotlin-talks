package org.sdkotlin.meetup

import java.time.DayOfWeek.*
import java.time.LocalDate.*
import java.time.temporal.TemporalAdjusters.*

fun firstWednesday() =
	now() == now().with(dayOfWeekInMonth(1, WEDNESDAY))

fun main(args: Array<String>) {
	when (firstWednesday()) {
		true -> println("SD Kotlin time!")
	}
}

