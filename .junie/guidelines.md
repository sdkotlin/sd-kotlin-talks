# SD Kotlin Project Guidelines

## Project Overview

This is a Kotlin-based project demonstrating various Kotlin programming concepts
and best practices through practical examples. The project also contains
reproducers for various upstream issues.

The project leverages modern Kotlin features including Coroutines, KSP (Kotlin
Symbol Processing), and functional programming with Arrow.

As this project is primarily just for code and technology demonstration
purposes, there are no tagged releases or published build output.

## Technology Stack

- **Kotlin**: Primary programming language
- **Java**: Comparative code examples
- **Gradle**: Build system
- **Arrow**: Functional programming
- **Koin**: Dependency injection
- **Coroutines**: Asynchronous programming
- **KSP and KotlinPoet**: Code generation

## Project Structure

```
.
├── .github/         # GitHub Actions CI workflows
├── .idea/           # IntelliJ IDEA configuration
├── build-logic/     # Included build with custom Gradle build logic
├── config/detekt/   # Detekt configuration files
├── detekt-rules/    # Included build with custom Detekt static code analysis rules
├── gradle/          # Gradle Wrapper configuration and version catalog
├── platforms/       # Gradle dependency management platforms
└── subprojects/     # Main project modules
```

## Build & Run

- The project uses Gradle as the build system
- Build and verify the project (includes unit and integration tests):
  `./gradlew build`
- Verify dependency configuration:
  `./gradlew buildHealth`
- Check for potential dependency updates:
  `./gradlew dependencyUpdates`

## Testing

- JUnit Jupiter is the primary testing framework
- AssertJ is used for fluent assertions
- Tests are organized into unit and integration suites
- Run unit tests: `./gradlew test`
- Run integration tests: `./gradlew integrationTest`
- Run all tests: `./gradlew check`
- JUnit 5's `@DynamicTest` feature is used for test parameterization

## Version Control

- The primary development branch is `main`
- Git LFS is used for versioning binaries

## Best Practices

1. **Code Organization**
	- Follow the versioned IntelliJ code style configuration and format all code
	  before commit
	- Keep modules focused and well-organized
	- Use appropriate package structures

2. **Testing**
	- Follow TDD principles where applicable
	- Write tests for new functionality
	- Use descriptive test names

3. **Dependencies**
	- Use the Gradle version catalog and platforms for dependency management
	- The GitHub Actions CI build will fail if `./gradlew buildHealth` does

4. **Quality**
	- As the project code sometimes contains partial demos, demonstrates
	  anti-patterns, reproduces issues, and uses preview features, it may result
	  in many compiler and static analysis warnings. No effort is made to
	  suppress these with `@Suppress` annotations, as that would excessively
	  clutter the examples.
