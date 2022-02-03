package org.sdkotlin.intro.kotlin._30_sealedtypes

import org.sdkotlin.intro.kotlin._30_sealedtypes.otherpackage.LocalPlayer
import org.sdkotlin.intro.kotlin._30_sealedtypes.otherpackage.RemotePlayer

// Kotlin has sealed classes and interfaces to represent a restricted hierarchy
// of types.

sealed class Player(open val name: String, open var health: Int)

// Sealed classes are similar to abstract classes. You can't directly
// instantiate them. Any constructors are implicitly protected.

fun `not gonna do it`() {

	//val player = Player("Timmy", 100) // Does not compile.
}

// Direct subclasses of sealed classes, that are themselves not also sealed,
// determine the exhaustively known part of that branch of the sealed type
// hierarchy.

/**
 * If the direct subclass is abstract or open, its subclasses can be defined in
 * other packages or modules.
 *
 * @see [LocalPlayer]
 * @see [RemotePlayer]
 */
abstract class HumanPlayer(
	name: String,
	health: Int = 100,
	open val topScore: Int = 0,
) : Player(name, health)

// Alternatively they can be data classes, which in turn makes them final.

data class NPC(override val name: String, override var health: Int = 100) :
	Player(name, health) {

	fun aiAttack() = "Coming to get ya"
}

// Sealed classes work well with sealed `when`s and smart casting.

fun topScore(player: Player) = when (player) {
	is NPC -> "N/A"
	is HumanPlayer -> player.topScore.toString()
	is OtherPlayerType -> "Unknown"
}

// Adding a type to the sealed hierarchy later will be called out by the
// compiler for exhaustive `when`s.

//class AlienPlayer : Player("Alf", 50)

fun main() {
	val bowser = NPC("Bowser")
	val mario = LocalPlayer(name = "Mario", topScore = 65)
	val luigi =
		RemotePlayer(name = "Luigi", ipAddress = "10.0.0.21", topScore = 70)

	val players = listOf(bowser, mario, luigi)

	players.forEach { player ->

		val action = when (player) {
			is NPC -> "${player.name}! ${player.aiAttack()} with " +
					"${player.health} health!"
			is RemotePlayer -> "${player.name} from ${player.ipAddress}! " +
					"Top Score: ${topScore(player)}."
			is HumanPlayer -> "${player.name}! Top Score: ${player.topScore}."
			is OtherPlayerType -> "${player.name}! Health: ${player.health}."
		}

		println(action)
	}
}
