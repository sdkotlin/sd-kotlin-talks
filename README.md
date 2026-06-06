[![Build Status](https://github.com/sdkotlin/sd-kotlin-talks/actions/workflows/gradle.yml/badge.svg)](https://github.com/sdkotlin/sd-kotlin-talks/actions/workflows/gradle.yml)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=sdkotlin_sd-kotlin-talks&metric=alert_status)](https://sonarcloud.io/dashboard?id=sdkotlin_sd-kotlin-talks)

# San Diego Kotlin User Group Talks

Code examples from
the [San Diego Kotlin User Group](https://www.meetup.com/sd-kotlin/) meetings on
the  [Kotlin](http://kotlinlang.org/) programming language.

## Building

The project is built
with [Gradle](https://docs.gradle.org/current/userguide/userguide.html) and uses
the [Gradle Wrapper](https://docs.gradle.org/current/userguide/gradle_wrapper.html#gradle_wrapper_reference),
which can be invoked from the project root once
the [Gradle prerequisites](https://docs.gradle.org/current/userguide/installation.html#sec:prerequisites)
are met.

The project uses [Gradle JVM Toolchains](https://docs.gradle.org/current/userguide/toolchains.html) to manage the build JDK, so you can
initiate a build with any JVM recent enough to start Gradle (see the
[Gradle prerequisites](https://docs.gradle.org/current/userguide/installation.html#sec:prerequisites)).

The primary build task is `build`.

Maintainer notes, including how to move to a new major Java version, are in
[DEVELOPING.md](DEVELOPING.md).
