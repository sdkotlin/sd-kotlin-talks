package org.sdkotlin.meetup;

import java.util.Objects;

public class DataClassesInJava {
	public static void main(final String[] args) {
		System.out.println(new Person("Luke"));
	}
}

class Person {

	private String name;
	private String favoriteProgrammingLangauge;

	public Person(final String name) {
		this(name, "Kotlin");
	}

	public Person(final String name, final String favoriteProgrammingLangauge) {
		this.name = name;
		this.favoriteProgrammingLangauge = favoriteProgrammingLangauge;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getFavoriteProgrammingLangauge() {
		return favoriteProgrammingLangauge;
	}

	public void setFavoriteProgrammingLangauge(final String favoriteProgrammingLangauge) {
		this.favoriteProgrammingLangauge = favoriteProgrammingLangauge;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		final Person person = (Person) o;
		return Objects.equals(name, person.name) &&
				Objects.equals(favoriteProgrammingLangauge, person.favoriteProgrammingLangauge);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, favoriteProgrammingLangauge);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("Person{");
		sb.append("name='").append(name).append('\'');
		sb.append(", favoriteProgrammingLangauge='").append(favoriteProgrammingLangauge).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
