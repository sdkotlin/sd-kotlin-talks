package org.sdkotlin.meetup;

import java.util.Objects;

public class DataClassesInJava {
	public static void main(String[] args) {

		System.out.println(new Person("Luke"));
	}
}

class Person {

	private String name;
	private String favoriteProgrammingLangauge;

	public Person(String name) {
		this(name, "Kotlin");
	}

	public Person(String name, String favoriteProgrammingLangauge) {
		this.name = name;
		this.favoriteProgrammingLangauge = favoriteProgrammingLangauge;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFavoriteProgrammingLangauge() {
		return favoriteProgrammingLangauge;
	}

	public void setFavoriteProgrammingLangauge(String favoriteProgrammingLangauge) {
		this.favoriteProgrammingLangauge = favoriteProgrammingLangauge;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Person person = (Person) o;
		return Objects.equals(name, person.name) &&
				Objects.equals(favoriteProgrammingLangauge, person.favoriteProgrammingLangauge);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, favoriteProgrammingLangauge);
	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("Person{");
		sb.append("name='").append(name).append('\'');
		sb.append(", favoriteProgrammingLangauge='").append(favoriteProgrammingLangauge).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
