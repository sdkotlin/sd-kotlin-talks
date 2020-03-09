package org.sdkotlin.meetup

import org.sdkotlin.meetup.inlineclasses.EventID
import org.sdkotlin.meetup.inlineclasses.MemberID
import org.sdkotlin.meetup.inlineclasses.PrimitiveTicket
import org.sdkotlin.meetup.inlineclasses.SeatID
import org.sdkotlin.meetup.inlineclasses.Ticket
import org.sdkotlin.meetup.inlineclasses.VenueID

fun bookTicket(memberId: Long, venueId: Long, eventId: Long, seatId: Long): PrimitiveTicket =
		PrimitiveTicket(memberId, venueId, eventId, seatId)

fun bookTicket(memberId: MemberID, venueId: VenueID, eventId: EventID, seatId: SeatID): Ticket =
		Ticket(memberId, venueId, eventId, seatId)
