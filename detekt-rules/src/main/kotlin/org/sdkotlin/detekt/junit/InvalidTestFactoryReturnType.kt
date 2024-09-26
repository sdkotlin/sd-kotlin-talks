package org.sdkotlin.detekt.junit

import io.gitlab.arturbosch.detekt.api.CodeSmell
import io.gitlab.arturbosch.detekt.api.Config
import io.gitlab.arturbosch.detekt.api.Debt
import io.gitlab.arturbosch.detekt.api.Entity
import io.gitlab.arturbosch.detekt.api.Issue
import io.gitlab.arturbosch.detekt.api.Rule
import io.gitlab.arturbosch.detekt.api.Severity
import io.gitlab.arturbosch.detekt.api.internal.RequiresTypeResolution
import org.jetbrains.kotlin.psi.KtNamedFunction
import org.jetbrains.kotlin.resolve.BindingContext
import org.jetbrains.kotlin.resolve.descriptorUtil.fqNameOrNull
import org.jetbrains.kotlin.types.KotlinType
import org.jetbrains.kotlin.types.typeUtil.supertypes

/**
 * A Detekt rule for ensuring
 * [JUnit 5 Dynamic Tests](https://junit.org/junit5/docs/current/user-guide/#writing-tests-dynamic-tests)
 * hava a valid inferred return type.
 */
@RequiresTypeResolution
class InvalidTestFactoryReturnType(config: Config) : Rule(config) {

	override val issue = Issue(
		id = "InvalidTestFactoryReturnType",
		severity = Severity.Defect,
		description = "JUnit @TestFactory functions must return a valid type.",
		debt = Debt.Companion.FIVE_MINS
	)

	override fun visitNamedFunction(function: KtNamedFunction) {
		super.visitNamedFunction(function)

		val isTestFactoryFunction =
			function.annotationEntries.any { it.text == "@TestFactory" }

		if (isTestFactoryFunction) {
			// Get the resolved return type of the function
			val returnType = function.resolveReturnType()

			if (returnType != null && !isValidTestFactoryReturnType(returnType)) {
				report(
					CodeSmell(
						issue,
						Entity.from(function),
						message = "JUnit @TestFactory functions must return " +
							"a valid type. Found: $returnType."
					)
				)
			}
		}
	}

	private fun KtNamedFunction.resolveReturnType(): KotlinType? {
		// Get the binding context and resolve the return type
		return bindingContext[BindingContext.FUNCTION, this]?.returnType
	}

	private fun isValidTestFactoryReturnType(type: KotlinType): Boolean {
		// Check if it's a subtype of DynamicNode (including DynamicTest and
		// DynamicContainer)
		if (type.isDynamicNode()
			|| type.supertypes().any { it.isDynamicNode() }
		) {
			return true
		}

		// Check if it's a container (e.g., Stream, Collection, Iterable,
		// Iterator, Array) of DynamicNode
		if (type.isDynamicNodeContainer()) {
			return true
		}

		return false
	}

	private fun KotlinType.isDynamicNode(): Boolean {
		val fqName =
			this.constructor.declarationDescriptor?.fqNameOrNull()?.asString()
		return fqName == "org.junit.jupiter.api.DynamicNode"
	}

	private fun KotlinType.isDynamicNodeContainer(): Boolean {
		val fqName =
			this.constructor.declarationDescriptor?.fqNameOrNull()?.asString()
		val isContainerType =
			fqName in listOf(
				"kotlin.Array",
				"kotlin.collections.Collection",
				"kotlin.collections.List",
				"kotlin.collections.Set",
				"kotlin.collections.Iterable",
				"kotlin.collections.Iterator",
				"java.util.stream.Stream",
			)

		return isContainerType && arguments.any { arg ->
			arg.type.isDynamicNode()
				|| arg.type.supertypes().any { it.isDynamicNode() }
		}
	}
}
