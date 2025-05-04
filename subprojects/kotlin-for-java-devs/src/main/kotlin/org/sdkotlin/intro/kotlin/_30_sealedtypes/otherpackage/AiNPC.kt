package org.sdkotlin.intro.kotlin._30_sealedtypes.otherpackage

import org.sdkotlin.intro.kotlin._30_sealedtypes.NPC

// Indirect subtypes of open types that are sealed subtypes are allowed in
// other packages and modules.

data class AiNPC(
	override val name: String,
	override var health: Int = 100,
) : NPC() {

	override fun attack() = "AI is coming to get ya!"
}
