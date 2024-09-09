package org.sdkotlin.intro.kotlin._30_sealedtypes

// Direct subclasses can be in other files so long as they're in the same
// package.

class UnknownPlayer : Player {
	override val name: String = "Unknown"
	override val health: Int = 0
}
