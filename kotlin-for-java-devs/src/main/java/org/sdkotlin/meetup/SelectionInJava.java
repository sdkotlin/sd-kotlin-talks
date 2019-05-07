package org.sdkotlin.meetup;

public class SelectionInJava {

	public static void main(final String[] args) {

		if (true) {
			System.out.println(true);
		} else if (false) {
			System.out.println(false);
		} else {
			System.out.println("Huh?");
		}

		final int x = (true) ? 1 : 0;

		switch (x) {
			case 0:
				System.out.println("x == 0");
			case 1:
				System.out.println("x == 1");
			case 2:
				System.out.println("x == 1 (Oops, forgot the break!)");
			default:
				System.out.println("Double oops!");
		}
	}
}
