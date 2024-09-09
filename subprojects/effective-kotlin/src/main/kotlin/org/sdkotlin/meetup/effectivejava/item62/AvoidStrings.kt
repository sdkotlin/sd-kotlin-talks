package org.sdkotlin.meetup.effectivejava.item62

/* Effective Java
Item 62: Avoid strings where other types are more appropriate
 */

// Data classes in Kotlin are so easy!

data class EmailAddress(val address: String, val name: String)

fun send(emailAddress: String) {
	println("Sending to $emailAddress")
}

// vs.

fun send(emailAddress: EmailAddress) {
	println("Sending to ${emailAddress.address}")
}

// It's safer and easier to add a "name" property to the data class in the future!
