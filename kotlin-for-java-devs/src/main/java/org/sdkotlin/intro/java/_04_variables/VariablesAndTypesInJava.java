package org.sdkotlin.intro.java._04_variables;

public class VariablesAndTypesInJava {

	// Fields get default values in Java

	boolean bool; // = false
	Boolean Bool; // = null
	byte b; // = 0
	Byte B; // = null
	char c; // = 'u0000', i.e. NULL
	Character C; // = null
	short s; // = 0
	Short S; // = null
	int i; // = 0
	Integer I; // = null
	long l; // = 0L
	Long L; // = null
	float f; // = 0.0f
	Float F; // = null
	double d; // = 0.0d
	Double D; // = null

	// They are mutable unless declared 'final'

	final int j = 0;

	void funWithVariables() {
		i++;

		// Cannot assign a value to final variable 'j'
		//j++;

		// Local variables are not given default values.
		int anotherI;

		// It's a compile time error to try to use them before they're initialized
		//System.out.println(anotherI);

		anotherI = 1;

		System.out.println(anotherI);

		// You can have late initialized immutable local variables in Java
		final int yetAnotherI;

		yetAnotherI = 1;

		// Once initialized they can't be changed
		//yetAnotherI++;
	}

	void funWithJava11TypeInference() {

		// Java 11 brings type inference for local variables

		var typeInferenceInJava11 = "This is new";

		final var immutable = "Like 'val' in Kotlin";

		// Type inferred variables must be initialized
		//var notGonnaDoIt;
	}

	// Type inference is not supported for fields in Java
	//var notHotDog = 1;
}
