package org.sdkotlin.intro.kotlin._30_sealedtypes

fun main() {

	// Sealed types from other modules can be smart cast by type.

	val output = when (val player = playerFactory()) {
		is HumanPlayer -> player.topScore
		is NPC -> player.attack()
		is UnknownPlayer -> player.name
	}

	println(output)
}
