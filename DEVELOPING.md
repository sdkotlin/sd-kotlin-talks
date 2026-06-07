# Developing

## Updating the Major Java Version

1. Set the `java` version in
   [`gradle/libs.versions.toml`](gradle/libs.versions.toml) (the single source
   for the compile and test toolchain).
2. Regenerate the daemon JVM criteria with `./gradlew updateDaemonJvm`; do not
   hand-edit the generated
   [`gradle/gradle-daemon-jvm.properties`](gradle/gradle-daemon-jvm.properties).
3. Set `java-version` in
   [`.github/workflows/gradle.yml`](.github/workflows/gradle.yml).
4. Validate with `./gradlew build` and `./gradlew buildHealth`.

Gradle must be able to run its daemon on the new version, not just target it, so
upgrade Gradle first if needed.
