package org.sdkotlin.intro.java._XX_uniontypes;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Random;

public class UnionTypesInJava {

	private final Random random = new Random();

	// Java 7 added limited support for union types in multicatch blocks.

	static class BaseException extends RuntimeException {

		public String baseMethod() {
			return "BaseException.baseMethod";
		}
	}

	static class AnException extends BaseException {

		public String aMethod() {
			return "AnException.aMethod";
		}
	}

	static class AnotherException extends BaseException {

		public String anotherMethod() {
			return "AnotherException.anotherMethod";
		}
	}

	void withMulticatch() {

		try {
			if (random.nextBoolean()) {
				throw new AnException();
			} else {
				throw new AnotherException();
			}
		} catch (final AnException | AnotherException e) {

			// "e" is either `AnException` or `AnotherException` (mouseover).

			// It's automatically upcast to the closest common supertype of the
			// two, `BaseException`.
			System.out.println(e.baseMethod());

			// We can test and cast the exception if we want to use its
			// specific API.

			// Java 16 added pattern matching for `instanceof` for this.

			if (e instanceof AnException anException) {

				// The declared `anException` is already cast to type
				// `AnException`.

				System.out.println(anException.aMethod());
			} else //noinspection ConstantConditions
				if (e instanceof AnotherException anotherException) {

					// IntelliJ knows this could just be an `else` on account of
					// the exhaustive union type.

					System.out.println(anotherException.anotherMethod());

					// However, if a third type was added to the union that
					// `else` would no longer be entirely valid. We might get a
					// `ClassCastException` at runtime with an `else`.

				} else if (e instanceof Exception) {

					// We could just catch the supertype of all exceptions to
					// address that, but we have to remember to do it, and it's
					// added verbosity for a case that shouldn't happen.

					e.printStackTrace();
				}

			// It would be better if the compiler caught such issues at
			// compile-time.

			// Java 17 includes pattern matching for switch statements as a
			// preview feature.

			switch (e) {
				case AnException an -> System.out.println(an.aMethod());
				case AnotherException another -> System.out.println(another.anotherMethod());

				// Unfortunately, at least in preview the compiler isn't smart
				// enough to understand exhaustive cases for multicatch union
				// types (even if the exception hierarchy were to be sealed),
				// so a default case is still required even though it could
				// never possibly occur.

				// At least the compiler does now force the "else" case.
				// (Try commenting out the below...)

				default -> e.printStackTrace();
			}
		}
	}

	void withOptional() {

		// Union types can be modelled with API.

		// Java 8 added `Optional<T>`, which is a union type that wraps
		// either a value of type `T`, or `null`. It's API encourages us to
		// safely ask which at compile-time if we want to unwrap and use the
		// value.

		final Optional<String> maybeAwesome = Optional.ofNullable(
				random.nextBoolean() ? "Awesome" : null
		);

		System.out.println(maybeAwesome.orElse("Not awesome"));

		// Unfortunately, the `Optional` API also includes several methods
		// that do result in runtime exceptions.

		try {

			@SuppressWarnings({"ConstantConditions", "unused"})
			final Optional<String> nope = Optional.of(null);
		} catch (final NullPointerException e) {
			System.out.println(" :( ");
		}

		try {

			final String nope = null;

			@SuppressWarnings({"ConstantConditions", "unused"})
			final String notGonnaDoIt = Optional.ofNullable(nope).get();
		} catch (final NoSuchElementException e) {
			System.out.println(" :/ ");
		}
	}

	// Another way to model union types with API is to used sealed type
	// hierarchies, which were added to Java 17.

	sealed interface EitherIntegerOrFloat permits IntegerWrapper, FloatWrapper {
		// No API of any use. Forces pattern matching.
	}

	non-sealed static class IntegerWrapper implements EitherIntegerOrFloat {

		public Integer getInteger() {
			return 1;
		}
	}

	non-sealed static class FloatWrapper implements EitherIntegerOrFloat {

		public Float getFloat() {
			return 1.0f;
		}
	}

	private EitherIntegerOrFloat getIntegerOrFloat() {

		return random.nextBoolean()
				? new IntegerWrapper()
				: new FloatWrapper();
	}

	void withSealedClassesAndInterfaces() {

		final var eitherIntegerOrFloat = getIntegerOrFloat();

		// No directly usable API, need to cast.
		//eitherIntegerOrFloat;

		// We can do exhaustively with the new pattern matching switch.

		switch (eitherIntegerOrFloat) {
			case IntegerWrapper integerWrapper -> {
				final Integer anInteger = integerWrapper.getInteger();
				System.out.println(anInteger);
			}
			case FloatWrapper floatWrapper -> {
				final Float aFloat = floatWrapper.getFloat();
				System.out.println(aFloat);
			}
			// Compiler knows no `default` branch is needed.
			// Code will stop compiling if a new sealed subtype is added.
		}
	}

	public static void main(final String[] args) {

		final var unionTypesInJava = new UnionTypesInJava();

		unionTypesInJava.withMulticatch();
		unionTypesInJava.withOptional();
		unionTypesInJava.withSealedClassesAndInterfaces();
	}
}
