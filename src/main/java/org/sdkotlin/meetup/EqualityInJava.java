package org.sdkotlin.meetup;

public class EqualityInJava {

	public static void main(String[] args) {

		System.out.println("foo" == new String("foo"));
		System.out.println("foo".equals(new String("foo")));
	}
}
