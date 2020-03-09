package org.sdkotlin.meetup

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.sdkotlin.meetup.inlineclasses.EventID
import org.sdkotlin.meetup.inlineclasses.MemberID
import org.sdkotlin.meetup.inlineclasses.PrimitiveTicket
import org.sdkotlin.meetup.inlineclasses.SeatID
import org.sdkotlin.meetup.inlineclasses.Ticket
import org.sdkotlin.meetup.inlineclasses.VenueID

class InlineClassesInKotlinTest {

	@Test
	fun `test inline class`() {

		val memberId = MemberID(1)

		assertThat(memberId.value).isEqualTo(1)
	}

	@Test
	fun `test bookTicket with primitives`() {

		val memberId: Long = 1
		val venueId: Long = 1
		val eventId: Long = 1
		val seatId: Long = 1

		println("   Primitive ticket info: memberId($memberId), venueId($venueId), eventId($eventId), seatId($seatId)")

		// See the bug?...
		val ticket = bookTicket(venueId, memberId, eventId, seatId)

		// And yet the test passes...
		assertThat(ticket).isEqualTo(PrimitiveTicket(memberId, venueId, eventId, seatId))
	}

	@Test
	fun `test bookTicket with inline classes`() {

		val memberId = MemberID(1)
		val venueId = VenueID(1)
		val eventId = EventID(1)
		val seatId = SeatID(1)

		println("   Inline class ticket info: $memberId, $venueId, $eventId, $seatId")

		val ticket = bookTicket(memberId, venueId, eventId, seatId)

		assertThat(ticket).isEqualTo(Ticket(memberId, venueId, eventId, seatId))
	}
}
