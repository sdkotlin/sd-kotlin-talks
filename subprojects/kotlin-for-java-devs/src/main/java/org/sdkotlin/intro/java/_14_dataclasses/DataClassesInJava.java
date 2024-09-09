package org.sdkotlin.intro.java._14_dataclasses;

import java.util.Objects;

public class DataClassesInJava {

	public static void main(final String[] args) {
		System.out.println(new Person("Luke"));
	}
}

class Person {

	private String name;
	private String favoriteProgrammingLanguage;

	public Person(final String name) {
		this(name, "Kotlin");
	}

	public Person(final String name, final String favoriteProgrammingLanguage) {
		this.name = name;
		this.favoriteProgrammingLanguage = favoriteProgrammingLanguage;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getFavoriteProgrammingLanguage() {
		return favoriteProgrammingLanguage;
	}

	public void setFavoriteProgrammingLanguage(final String favoriteProgrammingLanguage) {
		this.favoriteProgrammingLanguage = favoriteProgrammingLanguage;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		final var person = (Person) o;
		return Objects.equals(name, person.name) &&
				Objects.equals(favoriteProgrammingLanguage, person.favoriteProgrammingLanguage);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, favoriteProgrammingLanguage);
	}

	@Override
	public String toString() {
		final var sb = new StringBuilder("Person{");
		sb.append("name='").append(name).append('\'');
		sb.append(", favoriteProgrammingLanguage='").append(favoriteProgrammingLanguage).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
