package org.sdkotlin.buildergen.processor

import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.validate
import org.sdkotlin.buildergen.api.annotations.GeneratedBuilder

internal class BuilderGenSymbolProcessor(
	private val codeGenerator: CodeGenerator,
	private val logger: KSPLogger,
) : SymbolProcessor {

	override fun process(resolver: Resolver): List<KSAnnotated> {

		val annotationClass = GeneratedBuilder::class

		val symbols = resolver.getSymbolsWithAnnotation(
			annotationClass.qualifiedName
				?: throw IllegalArgumentException(
					"Could not get $annotationClass qualified name"
				)
		)

		val annotatedSymbols: List<KSAnnotated> =
			symbols.filter { !it.validate() }.toList()

		symbols
			.filter { it is KSClassDeclaration && it.validate() }
			.forEach {
				it.accept(
					visitor = BuilderGenVisitor(codeGenerator, logger),
					data = Unit
				)
			}

		return annotatedSymbols
	}
}
