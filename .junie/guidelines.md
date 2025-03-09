# SD Kotlin Project Guidelines

## Project Overview

This is a Kotlin-based project demonstrating various Kotlin programming concepts
and best practices through practical examples. The project also contains
reproducers for various issues in the dependencies used.

The project leverages modern Kotlin features including Coroutines, KSP (Kotlin
Symbol Processing), and functional programming with Arrow.

As this project is primarily just for code and technology demonstration
purposes, there are no tagged releases or published build outputs.

## Technology Stack

- **Kotlin**: Primary programming language
- **Java**: Comparative code examples
- **Gradle**: Build system
- **Arrow**: Functional programming
- **Koin**: Dependency injection
- **Coroutines**: Asynchronous programming
- **KSP and KotlinPoet**: Code generation

## Project Structure

```text
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
- Verify dependency configuration: `./gradlew buildHealth`
- Check for potential dependency updates: `./gradlew dependencyUpdates`

## Testing

- JUnit Jupiter is the primary testing framework
- AssertJ is used for fluent assertions
- Mockk is available for mocking
- Prefer plain Kotlin mocks
- Use Gradle [test fixtures](https://docs.gradle.org/current/userguide/java_testing.html#sec:java_test_fixtures) for shared test doubles and utilities
- Tests are organized into unit (`src/test`) and integration (`src/it`) suites
- JUnit 5's `@DynamicTest` feature is used for test parameterization
- Run unit tests: `./gradlew test`
- Run integration tests: `./gradlew integrationTest`
- Run all tests: `./gradlew check`

## Version Control

- The primary development branch is `main`
- Git LFS is used for versioning binaries

## Code Style

- Follow the versioned IntelliJ code style configuration
- Format all changed code before commit

## Dependencies

- Use the Gradle version catalog and platforms for dependency management
- The GitHub Actions CI build will fail if `./gradlew buildHealth` does

## Code Quality

- As the project code sometimes contains partial examples, antipattern
  demonstrations, issue reproducers, and uses preview features, it may contain
  many compiler, static analysis, and runtime warnings. No effort is made to
  suppress these with `@Suppress` annotations, as that would excessively
  clutter the examples.
