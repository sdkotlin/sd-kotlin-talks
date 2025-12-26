package org.sdkotlin.detekt.junit

import dev.detekt.api.RuleName
import dev.detekt.api.RuleSet
import dev.detekt.api.RuleSetProvider

class JUnitRuleSetProvider : RuleSetProvider {

	override val ruleSetId: RuleSet.Id = RuleSet.Id("junit")

	override fun instance(): RuleSet =
		RuleSet(
			ruleSetId,
			mapOf(
				RuleName("InvalidTestFactoryReturnType") to {
					InvalidTestFactoryReturnType(
						it
					)
				},
			),
		)
}
