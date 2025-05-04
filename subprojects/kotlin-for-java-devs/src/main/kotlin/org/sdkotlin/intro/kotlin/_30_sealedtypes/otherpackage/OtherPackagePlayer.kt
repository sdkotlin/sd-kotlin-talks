@file:Suppress("UnusedImport")

package org.sdkotlin.intro.kotlin._30_sealedtypes.otherpackage

// Direct subtypes of sealed types can't be in other packages.

// Does not compile...
/*
class OtherPackagePlayer(
	override val name: String,
	override var health: Int,
) : Player
*/
