package org.sdkotlin.intro.kotlin._30_sealedtypes

// Direct subclasses of sealed classes can't be in other modules.

// Does not compile...
/*
class OtherModulePlayer(
	override val name: String,
	override val health: Int,
) : Player
*/
