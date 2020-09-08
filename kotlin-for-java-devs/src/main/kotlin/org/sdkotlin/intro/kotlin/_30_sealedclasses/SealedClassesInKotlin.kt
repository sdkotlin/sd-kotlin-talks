package org.sdkotlin.intro.kotlin._30_sealedclasses

import org.sdkotlin.intro.kotlin._30_sealedclasses.otherpackage.LocalPlayer
import org.sdkotlin.intro.kotlin._30_sealedclasses.otherpackage.RemotePlayer

// Kotlin has sealed classes to represent a restricted hierarchy of types.

sealed class Player(open val name: String, open var health: Int)

// Sealed classes are similar to abstract classes. You can't directly
// instantiate them. Any constructors are implicitly private.

fun `not gonna do it`() {

	//val player = Player("Timmy") // Does not compile.
}

// All direct subclasses must be defined in the same file as the sealed class.

// The can be data classes, which in turn makes them final.

data class NPC(override val name: String, override var health: Int = 100) :
	Player(name, health) {

	fun aiAttack() = "Coming to get ya"
}

// If a subclass is open, its subclasses can be defined in other files or
// modules.

abstract class HumanPlayer(
	name: String,
	health: Int = 100,
	open val topScore: Int = 0
) : Player(name, health)

// Sealed classes work well with sealed `when`s and smart casting.

fun topScore(player: Player) = when (player) {
	is NPC -> "N/A"
	is HumanPlayer -> player.topScore.toString()
}

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
					"Top Score: ${player.topScore}."
			is HumanPlayer -> "${player.name}! Top Score: ${player.topScore}."
		}

		println(action)
	}
}
