package org.sdkotlin.detekt.junit

import dev.detekt.api.Config
import dev.detekt.api.Entity
import dev.detekt.api.Finding
import dev.detekt.api.RequiresAnalysisApi
import dev.detekt.api.Rule
import org.jetbrains.kotlin.analysis.api.KaExperimentalApi
import org.jetbrains.kotlin.analysis.api.KaSession
import org.jetbrains.kotlin.analysis.api.analyze
import org.jetbrains.kotlin.analysis.api.types.KaClassType
import org.jetbrains.kotlin.analysis.api.types.KaType
import org.jetbrains.kotlin.name.ClassId
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.psi.KtNamedFunction

/**
 * A Detekt rule for ensuring
 * [JUnit 5 Dynamic Tests](https://junit.org/junit5/docs/current/user-guide/#writing-tests-dynamic-tests)
 * have a valid inferred return type.
 */
class InvalidTestFactoryReturnType(config: Config) :
	Rule(config, "JUnit @TestFactory functions must return a valid type."),
	RequiresAnalysisApi {

	companion object {
		private val TEST_FACTORY_ANNOTATION =
			ClassId.topLevel(FqName("org.junit.jupiter.api.TestFactory"))
		private val DYNAMIC_NODE =
			ClassId.topLevel(FqName("org.junit.jupiter.api.DynamicNode"))

		private val DYNAMIC_NODE_CONTAINERS: Set<ClassId> = setOf(
			ClassId.topLevel(FqName("kotlin.Array")),
			ClassId.topLevel(FqName("kotlin.collections.Collection")),
			ClassId.topLevel(FqName("kotlin.collections.List")),
			ClassId.topLevel(FqName("kotlin.collections.Set")),
			ClassId.topLevel(FqName("kotlin.collections.Iterable")),
			ClassId.topLevel(FqName("kotlin.collections.Iterator")),
			ClassId.topLevel(FqName("kotlin.sequences.Sequence")),
			ClassId.topLevel(FqName("java.util.stream.Stream")),
		)
	}

	@OptIn(KaExperimentalApi::class)
	override fun visitNamedFunction(function: KtNamedFunction) {
		super.visitNamedFunction(function)

		analyze(function) {
			val symbol = function.symbol
			val isTestFactoryFunction =
				symbol.annotations.any { it.classId == TEST_FACTORY_ANNOTATION }

			if (isTestFactoryFunction) {
				val returnType = symbol.returnType

				if (!isValidTestFactoryReturnType(returnType)) {
					report(
						Finding(
							Entity.from(function),
							message = "JUnit @TestFactory functions must " +
								"return a valid type. Found: $returnType."
						)
					)
				}
			}
		}
	}

	private fun KaSession.isValidTestFactoryReturnType(type: KaType): Boolean {
		return isDynamicNodeOrSubtype(type) || isDynamicNodeContainer(type)
	}

	private fun KaSession.isDynamicNodeOrSubtype(type: KaType): Boolean {
		return type.isDynamicNode() ||
			type.allSupertypes.any { it.isDynamicNode() }
	}

	private fun KaType.isDynamicNode(): Boolean {
		val classId = (this as? KaClassType)?.classId
		return classId == DYNAMIC_NODE
	}

	private fun KaSession.isDynamicNodeContainer(type: KaType): Boolean {
		val classType = type as? KaClassType ?: return false
		val classId = classType.classId

		// Accept both the known container interfaces/classes and any subtype
		// of them
		val isContainerType =
			classId in DYNAMIC_NODE_CONTAINERS ||
				classType.allSupertypes
					.mapNotNull { (it as? KaClassType)?.classId }
					.any { it in DYNAMIC_NODE_CONTAINERS }

		if (!isContainerType) return false

		return classType.typeArguments.any { arg ->
			val argType = arg.type ?: return@any false
			isDynamicNodeOrSubtype(argType)
		}
	}
}
