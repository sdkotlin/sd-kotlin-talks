package org.sdkotlin.intro.kotlin._30_sealedtypes

import org.sdkotlin.intro.kotlin._30_sealedtypes.otherpackage.AiNPC

// Kotlin has sealed classes and interfaces to represent a restricted hierarchy
// of types.

/**
 * Direct subtypes of sealed types must be in the same package and module.
 *
 * @see UnknownPlayer
 *
 * Also see OtherPackagePlayer.kt, and OtherModulePlayer.kt.
 */
sealed interface Player {
	val name: String
	val health: Int
}

// Declaring a subtype to also be sealed extends the depth of the sealed
// hierarchy.

sealed class HumanPlayer : Player {
	abstract val topScore: Int
}

// Sealed classes are similar to abstract classes. You can't directly
// instantiate them. Any constructors are implicitly protected.

fun notGonnaDoIt() {

	//val player = HumanPlayer() // Does not compile.
}

// Direct subclasses of sealed classes that are not sealed determine the
// exhaustively known part of that branch of the sealed type hierarchy.

data class LocalPlayer(
	override val name: String,
	override val health: Int = 100,
	override val topScore: Int = 0,
) : HumanPlayer()

data class RemotePlayer(
	override val name: String,
	override val health: Int = 100,
	override val topScore: Int = 0,
	val ipAddress: String,
) : HumanPlayer()

/**
 * If the direct subclass is abstract or open, its subclasses can be defined in
 * other packages or modules.
 *
 * @see [AiNPC]
 */
abstract class NPC : Player {
	abstract fun attack(): String
}

// Sealed classes work well with sealed `when`s and smart casting.

fun topScore(player: Player) = when (player) {
	is HumanPlayer -> player.topScore.toString()
	is NPC -> "N/A"
	is UnknownPlayer -> "Unknown"
}

// Adding a type to the sealed hierarchy later will be called out by the
// compiler for exhaustive `when`s.

//class AlienPlayer(override val name: String, override var health: Int) : Player

fun main() {
	notGonnaDoIt()
	val bowser = AiNPC("Bowser")
	val mario = LocalPlayer(name = "Mario", topScore = 65)
	val luigi =
		RemotePlayer(name = "Luigi", ipAddress = "10.0.0.21", topScore = 70)

	val players = listOf(bowser, mario, luigi)

	players.forEach { player ->

		val intro = when (player) {
			is NPC -> "${player.name}! ${player.attack()} with " +
					"${player.health} health!"
			is RemotePlayer -> "${player.name} from ${player.ipAddress}! " +
					"Top Score: ${topScore(player)}."
			is HumanPlayer -> "${player.name}! Top Score: ${player.topScore}."
			is UnknownPlayer -> "${player.name}! Health: ${player.health}."
		}

		println(intro)
	}
}

fun playerFactory(): Player = AiNPC("Big Boss")
