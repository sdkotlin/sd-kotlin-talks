package org.sdkotlin.intro.kotlin._30_sealedtypes.otherpackage

import org.sdkotlin.intro.kotlin._30_sealedtypes.NPC

// Indirect subtypes of sealed types can be in other modules.

class OtherModuleAndPackageNPC : NPC() {

	override val name: String = "Invincible"
	override val health: Int = 100

	override fun attack() = "$name attack!"
}

fun main() {
	println(OtherModuleAndPackageNPC().attack())
}
