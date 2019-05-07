package org.sdkotlin.meetup;

public class VariablesAndTypesInJava {

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

	final int j = 0;

	void funWithVariables() {
		i++;
		// j++; // Does not compile
	}
}
