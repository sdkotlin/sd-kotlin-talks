package org.sdkotlin.intro.kotlin._31_inlineclasses

import org.sdkotlin.intro.kotlin._31_inlineclasses.entities.EventID
import org.sdkotlin.intro.kotlin._31_inlineclasses.entities.MemberID
import org.sdkotlin.intro.kotlin._31_inlineclasses.entities.PrimitiveTicket
import org.sdkotlin.intro.kotlin._31_inlineclasses.entities.SeatID
import org.sdkotlin.intro.kotlin._31_inlineclasses.entities.Ticket
import org.sdkotlin.intro.kotlin._31_inlineclasses.entities.VenueID

fun bookTicket(
	memberId: Long,
	venueId: Long,
	eventId: Long,
	seatId: Long
): PrimitiveTicket =
	PrimitiveTicket(memberId, venueId, eventId, seatId)

fun bookTicket(
	memberId: MemberID,
	venueId: VenueID,
	eventId: EventID,
	seatId: SeatID
): Ticket =
	Ticket(memberId, venueId, eventId, seatId)
