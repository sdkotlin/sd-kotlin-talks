package org.sdkotlin.intro.kotlin._30_sealedtypes.otherpackage

// Direct subclasses of sealed classes can't be in other packages. Does not
// compile...

/*
data class OtherPlayerType(
	override val name: String,
	override var health: Int,
) : Player(name, health)
*/
