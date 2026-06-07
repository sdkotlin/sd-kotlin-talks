package org.sdkotlin.intro.java._13_constructors;

public class ConstructorsAndInitializersInJava {

	static void main() {

		// Unlike Kotlin's primary constructor, a Java class declares its fields
		// and assigns them explicitly in a constructor body.

		new Rebel("Luke");
		new Rebel("Han", false);

		// Java has no default arguments, so overloaded constructors that
		// delegate with 'this(...)' stand in for them.

		new Hutt("Jabba", true);

		// Static initializers run once, when the class is first loaded. Instance
		// field initializers and initializer blocks run in source order, before
		// the constructor body, on every instantiation. Creating two Ewoks
		// prints "Ewok class loaded" once but the instance output twice.

		new Ewok("Wicket");
		new Ewok("Teebo");
	}
}

class Rebel {

	private final String name;
	private final boolean jedi;

	Rebel(final String name) {
		// A 'this(...)' delegating call must be the first statement.
		this(name, true);
	}

	Rebel(final String name, final boolean jedi) {
		this.name = name;
		this.jedi = jedi;
		System.out.println(this.name + " (jedi=" + this.jedi + ")");
	}
}

class Hutt {

	private final String name;
	private final boolean isJabba;

	Hutt(final String name) {
		this(name, false);
	}

	Hutt(final String name, final boolean isJabba) {
		this.name = name;
		this.isJabba = isJabba;
		System.out.println(this.name + " (isJabba=" + this.isJabba + ")");
	}
}

class Ewok {

	// A static initializer runs once, when the class is first loaded, together
	// with static field initializers in source order.

	static {
		System.out.println("Ewok class loaded");
	}

	private static final String SPECIES = "Ewok";

	// Instance initializer blocks and field initializers run in source order,
	// before the constructor body, on every instantiation. This mirrors a
	// Kotlin 'init' block.

	{
		System.out.println("Initializing an " + SPECIES);
		// System.out.println(name); // Illegal forward reference: does not compile.
	}

	private final String name;

	Ewok(final String nameArg) {
		System.out.println(nameArg);
		this.name = nameArg.toUpperCase();
		System.out.println(this.name);
	}
}
