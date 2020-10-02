package org.sdkotlin.intro.kotlin._XX_inlineclasses.entities

data class Ticket(
	val memberID: MemberID, val venueID: VenueID, val eventID: EventID,
	val seatID: SeatID
) {

	// Platform declaration clash.
	// Per the KEEP, functions can be mangled, but constructors can not:
	// https://github.com/Kotlin/KEEP/blob/master/proposals/inline-classes.md
// 	constructor(memberID: Long, venueID: Long, eventID: Long, seatID: Long) :
//			this(MemberID(memberID), VenueID(venueID), EventID(eventID), SeatID(seatID))
}

data class PrimitiveTicket(
	val memberID: Long, val venueID: Long, val eventID: Long,
	val seatID: Long
)
