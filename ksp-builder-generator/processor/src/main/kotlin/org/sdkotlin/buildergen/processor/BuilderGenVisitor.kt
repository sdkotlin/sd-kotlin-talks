package org.sdkotlin.buildergen.processor

import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSFunctionDeclaration
import com.google.devtools.ksp.symbol.KSName
import com.google.devtools.ksp.symbol.KSTypeReference
import com.google.devtools.ksp.symbol.KSValueParameter
import com.google.devtools.ksp.symbol.KSVisitorVoid
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeSpec
import com.squareup.kotlinpoet.asClassName
import com.squareup.kotlinpoet.ksp.toClassName
import com.squareup.kotlinpoet.ksp.toTypeName
import com.squareup.kotlinpoet.ksp.writeTo
import org.sdkotlin.buildergen.api.builder.Builder

internal class BuilderGenVisitor(
	private val codeGenerator: CodeGenerator,
	private val logger: KSPLogger,
) : KSVisitorVoid() {

	override fun visitClassDeclaration(
		classDeclaration: KSClassDeclaration,
		data: Unit,
	) {
		classDeclaration.primaryConstructor!!.accept(this, data)
	}

	override fun visitFunctionDeclaration(
		function: KSFunctionDeclaration,
		data: Unit,
	) {
		val entityKSClassDeclaration: KSClassDeclaration =
			function.parentDeclaration as KSClassDeclaration

		val entityPackageName: String =
			entityKSClassDeclaration.containingFile!!.packageName.asString()

		val entityClassName: String =
			entityKSClassDeclaration.simpleName.asString()

		val entityKPClassName: ClassName =
			entityKSClassDeclaration.toClassName()

		val entityBuilderClassName = "${entityClassName}Builder"

		val entityBuilderKPClassName =
			ClassName(entityPackageName, entityBuilderClassName)

		val builderInterfaceKPClassName: ClassName =
			Builder::class.asClassName()

		val entityClassTypeSpecBuilder: TypeSpec.Builder =
			TypeSpec.classBuilder(entityBuilderKPClassName)

		entityClassTypeSpecBuilder
			.addSuperinterface(
				builderInterfaceKPClassName
					.parameterizedBy(entityKPClassName)
			)

		function.parameters.forEach { parameter: KSValueParameter ->

			val parameterName: KSName = parameter.name!!
			val parameterType: KSTypeReference = parameter.type

			entityClassTypeSpecBuilder.addProperty(
				PropertySpec.builder(
					parameterName.asString(),
					parameterType.toTypeName()
				)
					.mutable(true)
					.addModifiers(KModifier.LATEINIT)
					.build()
			)
		}

		entityClassTypeSpecBuilder.addFunction(
			FunSpec.builder("build")
				.addModifiers(KModifier.OVERRIDE)
				.returns(entityKPClassName)
				// TODO: Complete builder implementation
				.addStatement("TODO()")
				.build()
		)

		val entityBuilderTypeSpec: TypeSpec =
			entityClassTypeSpecBuilder.build()

		val entityBuilderFileSpecBuilder: FileSpec.Builder =
			FileSpec.builder(entityBuilderKPClassName)

		entityBuilderFileSpecBuilder.addType(
			entityBuilderTypeSpec
		)

		val entityBuilderFileSpec: FileSpec =
			entityBuilderFileSpecBuilder
				.build()

		entityBuilderFileSpec.writeTo(codeGenerator, aggregating = false)
	}
}
