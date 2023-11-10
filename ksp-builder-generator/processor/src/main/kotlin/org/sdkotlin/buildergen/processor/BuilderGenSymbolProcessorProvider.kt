package org.sdkotlin.buildergen.processor

import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.processing.SymbolProcessorProvider

class BuilderGenSymbolProcessorProvider : SymbolProcessorProvider {

	override fun create(
		environment: SymbolProcessorEnvironment,
	): SymbolProcessor =
		BuilderGenSymbolProcessor()
}
