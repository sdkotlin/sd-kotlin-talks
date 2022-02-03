package org.sdkotlin.intro.kotlin._30_sealedtypes.otherpackage

import org.sdkotlin.intro.kotlin._30_sealedtypes.HumanPlayer

data class LocalPlayer(
	override val name: String,
	override var health: Int = 100,
	override val topScore: Int = 0,
) : HumanPlayer(name, health, topScore)

data class RemotePlayer(
	override val name: String,
	val ipAddress: String,
	override var health: Int = 100,
	override val topScore: Int = 0,
) : HumanPlayer(name, health, topScore)
