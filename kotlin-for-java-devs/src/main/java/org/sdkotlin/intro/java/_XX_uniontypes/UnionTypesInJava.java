package org.sdkotlin.intro.java._XX_uniontypes;

import java.util.Random;
import java.util.function.Supplier;

public class UnionTypesInJava {

	void withMulticatch() {

		try {
			throw new AnException();

			// Java 7 added limited support for union types in multicatch blocks.
		} catch (final AnException | AnotherException e) {

			// "e" is either `AnException` or `AnotherException` (mouseover).
			// It's automatically upcast to the closest common supertype of the
			// two, `BaseException`.
			System.out.println(e.baseMethod());

			// Java 17 includes pattern matching for switch statements as a
			// preview feature.
			switch (e) {
				case AnException anException ->
						System.out.println(anException.aMethod());
				case AnotherException anotherException ->
						System.out.println(anotherException.anotherMethod());
				// Unfortunately, the compiler isn't smart enough to understand
				// exhaustive cases for multicatch union types (even if the
				// exception hierarchy were to be sealed), so a default case is
				// required even though it could never possibly occur.
				default -> throw new IllegalStateException("Can't happen!");
			}
		}
	}

	private EitherIntegerOrFloat getIntegerOrFloat() {

		return (new Random().nextBoolean())
				? new IntegerSupplier() : new FloatSupplier();
	}

	void withSealedClassesAndInterfaces() {

		final var eitherIntegerOrFloat = getIntegerOrFloat();

		// Does not compile, need to cast.
		//eitherIntegerOrFloat.get();

		// Which we can do exhaustively with the a pattern matching switch.
		switch (eitherIntegerOrFloat) {
			case IntegerSupplier integerSupplier ->
					System.out.println(integerSupplier.get());
			case FloatSupplier floatSupplier ->
					System.out.println(floatSupplier.get());
			// Compiler knows no default branch is needed.
			// Code will stop compiling if a new sealed subtype is added.
		}
	}

	public static void main(final String[] args) {

		final var unionTypesInJava = new UnionTypesInJava();

		unionTypesInJava.withMulticatch();
		unionTypesInJava.withSealedClassesAndInterfaces();
	}
}

class BaseException extends RuntimeException {

	public String baseMethod() {
		return "BaseException.baseMethod";
	}
}

class AnException extends BaseException {

	public String aMethod() {
		return "AnException.aMethod";
	}
}

class AnotherException extends BaseException {

	public String anotherMethod() {
		return "AnotherException.anotherMethod";
	}
}

sealed interface EitherIntegerOrFloat permits IntegerSupplier, FloatSupplier {
	// No API of any use. Forces pattern matching.
}

non-sealed class IntegerSupplier
		implements EitherIntegerOrFloat, Supplier<Integer> {

	@Override
	public Integer get() {
		return 1;
	}
}

non-sealed class FloatSupplier
		implements EitherIntegerOrFloat, Supplier<Float> {

	@Override
	public Float get() {
		return 1.0f;
	}
}
