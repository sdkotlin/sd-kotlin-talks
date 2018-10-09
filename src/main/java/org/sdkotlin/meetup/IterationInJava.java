package org.sdkotlin.meetup;

public class IterationInJava {
	public static void main(final String[] args) {

		for (int i = 1; i <= 5; i++) {
			System.out.println("Counting... " + i);
		}

		for (int i = 5; i >= 1; i--) {
			System.out.println("Down... " + i);
		}

		for (int i = 1; i <= 5; i += 2) {
			System.out.println("Odds... " + i);
		}

		final char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();

		for (final char l : alphabet) {
			System.out.println("Letters... " + l);
		}

		for (int i = 0; i < alphabet.length; i++) {
			System.out.println("Letter " + (i + 1) + " of the alphabet is " + alphabet[i]);
		}

		int i = 1;

		while(i <= 5) {
			System.out.println("Counting between 1 and 5: " + i++);
		}
	}
}
