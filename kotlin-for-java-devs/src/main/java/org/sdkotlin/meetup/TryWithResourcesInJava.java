package org.sdkotlin.meetup;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;

public class TryWithResourcesInJava {

	public static void main(final String[] args) throws URISyntaxException {

		final ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		final URL resource = classLoader.getResource("hello.txt");
		if (resource == null) {
			throw new IllegalArgumentException("File not found!");
		}
		final Path path = Paths.get(resource.toURI());

		try (final Stream<String> lines = Files.lines(path)) {

			final String data = lines.collect(joining("\n"));

			System.out.println(data);
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}
}
