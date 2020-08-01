package org.sdkotlin.intro.kotlin._XX_try_w_resources

import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths
import java.util.stream.Collectors.joining

fun main() {

	val classLoader = Thread.currentThread().contextClassLoader
	val resource = classLoader.getResource("hello.txt")
			?: throw IllegalArgumentException("File not found!")
	val path = Paths.get(resource.toURI())

	try {
		Files.lines(path).use { lines ->

			val data = lines.collect(joining("\n"))

			println(data)
		}
	} catch (e: IOException) {
		e.printStackTrace()
	}
}
