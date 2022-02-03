package org.sdkotlin.intro.kotlin._30_sealedtypes

import org.sdkotlin.intro.kotlin._30_sealedtypes.otherpackage.OtherPlayer

// Direct subclasses can be in other files so long as they're in the same
// package.

data class OtherPlayerType(
	override val name: String,
	override var health: Int,
) : Player(name, health)

fun main() {
	println(OtherPlayerType(name = "Unknown", health = 0))
}
