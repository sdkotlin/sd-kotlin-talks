package org.sdkotlin.detekt.junit

import io.gitlab.arturbosch.detekt.api.Config
import io.gitlab.arturbosch.detekt.api.RuleSet
import io.gitlab.arturbosch.detekt.api.RuleSetProvider

class JUnitRuleSetProvider : RuleSetProvider {

	override val ruleSetId: String = "junit"

	override fun instance(config: Config): RuleSet =
		RuleSet(
			ruleSetId,
			listOf(
				InvalidTestFactoryReturnType(config),
			),
		)
}
