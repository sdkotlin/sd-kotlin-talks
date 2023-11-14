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
import com.squareup.kotlinpoet.KModifier.OVERRIDE
import com.squareup.kotlinpoet.MemberName
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeSpec
import com.squareup.kotlinpoet.asClassName
import com.squareup.kotlinpoet.ksp.toClassName
import com.squareup.kotlinpoet.ksp.toTypeName
import com.squareup.kotlinpoet.ksp.writeTo
import org.sdkotlin.buildergen.api.builder.Builder
import kotlin.properties.Delegates

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

		val entityPackageNameString: String =
			entityKSClassDeclaration.containingFile!!.packageName.asString()

		val entityClassNameString: String =
			entityKSClassDeclaration.simpleName.asString()

		val entityClassName: ClassName =
			entityKSClassDeclaration.toClassName()

		val entityBuilderClassName =
			ClassName(
				entityPackageNameString,
				"${entityClassNameString}Builder"
			)

		val builderInterfaceClassName: ClassName =
			Builder::class.asClassName()

		val entityClassTypeSpecBuilder: TypeSpec.Builder =
			TypeSpec.classBuilder(entityBuilderClassName)

		entityClassTypeSpecBuilder
			.addSuperinterface(
				builderInterfaceClassName
					.parameterizedBy(entityClassName)
			)

		val buildMethodBodyStringBuilder: StringBuilder =
			StringBuilder().apply {
				appendLine("return %T(")
			}

		function.parameters.forEach { parameter: KSValueParameter ->

			val parameterKSName: KSName = parameter.name!!
			val parameterKSTypeReference: KSTypeReference = parameter.type

			val entityBuilderPropertySpecBuilder: PropertySpec.Builder =
				PropertySpec.builder(
					parameterKSName.asString(),
					parameterKSTypeReference.toTypeName()
				).mutable(true)

			if (parameterKSTypeReference.resolve().isMarkedNullable) {

				entityBuilderPropertySpecBuilder.initializer("%L", null)

			} else {

				val notNullMemberName =
					MemberName(Delegates::class.asClassName(), "notNull")

				entityBuilderPropertySpecBuilder.delegate(
					"%M()",
					notNullMemberName
				)
			}

			entityClassTypeSpecBuilder.addProperty(
				entityBuilderPropertySpecBuilder.build()
			)

			buildMethodBodyStringBuilder
				.appendLine("${parameterKSName.asString()}, ")
		}

		buildMethodBodyStringBuilder.appendLine(")")

		entityClassTypeSpecBuilder.addFunction(
			FunSpec.builder("build")
				.addModifiers(OVERRIDE)
				.returns(entityClassName)
				.addCode(
					buildMethodBodyStringBuilder.toString(),
					entityClassName
				)
				.build()
		)

		val entityBuilderFileSpecBuilder: FileSpec.Builder =
			FileSpec.builder(entityBuilderClassName)

		entityBuilderFileSpecBuilder.addType(
			entityClassTypeSpecBuilder.build()
		)

		val entityBuilderFileSpec: FileSpec =
			entityBuilderFileSpecBuilder.build()

		entityBuilderFileSpec.writeTo(codeGenerator, aggregating = false)
	}
}
