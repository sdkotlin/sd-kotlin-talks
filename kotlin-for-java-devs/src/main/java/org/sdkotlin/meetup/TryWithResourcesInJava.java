package org.sdkotlin.meetup;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static java.util.stream.Collectors.joining;

public class TryWithResourcesInJava {

	public static void main(final String[] args) throws URISyntaxException {

		final var classLoader = Thread.currentThread().getContextClassLoader();
		final var resource = classLoader.getResource("hello.txt");
		if (resource == null) {
			throw new IllegalArgumentException("File not found!");
		}
		final var path = Paths.get(resource.toURI());

		try (final var lines = Files.lines(path)) {

			final var data = lines.collect(joining("\n"));

			System.out.println(data);
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}
}
