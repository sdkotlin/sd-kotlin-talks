package org.sdkotlin.intro.kotlin._XX_stdlib

import kotlin.io.encoding.Base64
import kotlin.time.Clock
import kotlin.time.Instant
import kotlin.time.toJavaInstant
import kotlin.uuid.Uuid
import kotlin.uuid.toJavaUuid

// The standard library has grown its own versions of types Java developers
// already have in the JDK. They exist because the standard library is
// multiplatform and `java.*` is not, so on the JVM they buy portability
// rather than capability.

// Each of the following is stable and needs no opt-in as of Kotlin 2.4.

fun `with uuids`() {

	// `kotlin.uuid.Uuid` covers what `java.util.UUID` does.

	val uuid = Uuid.random()

	val parsed = Uuid.parse("550e8400-e29b-41d4-a716-446655440000")

	// On the JVM the two convert both ways, so interop is a call rather than
	// a rewrite.

	val javaUuid: java.util.UUID = uuid.toJavaUuid()

	println("uuid: $uuid, parsed: $parsed, as java.util.UUID: $javaUuid")
}

fun `with instants`() {

	// `kotlin.time.Clock` and `kotlin.time.Instant` cover `java.time`'s
	// `Clock` and `Instant`. Taking a `Clock` as a parameter keeps `now()`
	// out of the code under test, the same way `java.time.Clock` does.

	val now: Instant = Clock.System.now()

	val javaInstant: java.time.Instant = now.toJavaInstant()

	println("now: $now, as java.time.Instant: $javaInstant")
}

fun `with encodings`() {

	val bytes = "Kotlin".encodeToByteArray()

	// `java.util.Base64` has been in the JDK since 8 and `java.util.HexFormat`
	// since 17. The standard library equivalents are multiplatform, with hex
	// as extensions on `ByteArray` and `String`.

	val encoded = Base64.Default.encode(bytes)

	val hex = bytes.toHexString()

	println("base64: $encoded, hex: $hex")
}

fun main() {
	`with uuids`()
	`with instants`()
	`with encodings`()
}
