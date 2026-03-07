# AGENTS.md

## Repository purpose
This repository is primarily a tech demonstrator and evaluator for Kotlin, Gradle, and related tooling.

Optimize for:
- clarity
- correctness
- maintainability
- focused experimentation

Do not assume every module is production-oriented. Some modules exist to evaluate tools, libraries, patterns, or build techniques.

Some code intentionally demonstrates alternative approaches or anti-patterns for educational purposes. Do not remove or simplify such examples unless explicitly asked.

## Working style
- Keep diffs focused and minimal.
- Do not perform unrelated refactors.
- Preserve existing project structure and naming conventions.
- Follow existing patterns in build logic, module organization, and version catalog usage.
- Prefer idiomatic Kotlin and Gradle Kotlin DSL.
- Favor explicit, readable build logic over clever or compressed DSL.

## Git workflow
Agents should not run `git commit`, `git push`, or create branches unless explicitly instructed.
Prepare clean, reviewable working tree changes and stop after validation so the maintainer can review and commit manually.

## Warnings and inspections
Some source files intentionally contain compilation warnings or IDE inspection warnings to demonstrate incorrect patterns or deprecated APIs.

Do not remove warnings or suppress them unless the task explicitly requires it.

## Version catalog guidance
This project uses a Gradle version catalog in `libs.versions.toml`.

When updating dependencies or plugins:
- Prefer updating catalog versions rather than inlining versions elsewhere.
- Do not rename catalog aliases, bundles, or plugin aliases unless explicitly asked.
- After upgrading a dependency, search the repository for usages of its alias to ensure examples still compile.

## Dependency update workflow
A common task in this repository is checking, upgrading, and validating dependencies.

To check for dependency updates:

`./gradlew dependencyUpdates --no-parallel`

When updating dependencies:
- Prefer updating versions in `libs.versions.toml` when applicable.
- Upgrade one dependency at a time whenever practical.
- Structure changes so each dependency upgrade could be committed independently, but do **not** create commits. Leave changes for the repository maintainer to review and commit.
- Keep version catalog edits consistent with existing style.
- Watch for compatibility issues across Gradle, Kotlin, plugins, and test libraries.
- Call out any deprecations, warnings, behavioral changes, or required source changes introduced by the upgrade.

When dependency upgrades introduce improved APIs, patterns, or idioms:
- Prefer updating examples to demonstrate the modern approach.
- Replace deprecated APIs with their recommended replacements.
- Update demonstration code to reflect current best practices where practical.
- If both old and new approaches are educational, it is acceptable to keep both examples.
- Prefer modernizing examples to demonstrate best practices introduced by the upgraded dependency.

## Gradle task discovery
Do not assume task names beyond those documented here.

If a task is needed:
- inspect the build scripts first
- prefer discovering tasks rather than guessing
- do not introduce new tasks unless explicitly asked

## Validation
After a change or dependency upgrade, verify the build with:

`./gradlew build --rerun-tasks`

and:

`./gradlew buildHealth`

Use the smallest sufficient validation for the scope of the change first, but for dependency, wrapper, or build-logic changes these full validations are preferred.

## Gradle wrapper upgrades
When upgrading Gradle:

1. Update the Gradle version in the `Wrapper` task configuration in the root `build.gradle.kts`.

2. Run:

   `./gradlew wrapper`

3. Run any Gradle task to bootstrap the new wrapper distribution:

   `./gradlew help`

## Build logic safety
Changes under `build-logic` affect the entire repository.

When modifying build logic:
- keep changes small and targeted
- preserve existing plugin and DSL patterns unless there is a clear reason to change them
- modernize build logic when doing so is part of a dependency upgrade or improves demonstrated practices
- validate carefully because build-logic changes impact multiple modules

## Change expectations
When making changes:
- explain what changed and why
- report what validation was run
- separate follow-up ideas from completed work
- do not create Git commits; stop after changes are validated
- avoid bundling speculative cleanup into the same change

For dependency upgrades, summarize both the version change and any example modernizations performed because of it.

If a change could alter the intent of a demonstration or example, ask for clarification rather than modifying it.

## Guardrails
- Do not introduce new dependencies without clear justification.
- Do not change unrelated versions while performing a targeted upgrade.
- Do not remove demonstrator or experimental code unless explicitly asked.
- Prefer version-catalog-based dependency management where the project already uses it.

## Generated and build outputs
Do not modify generated sources or build output directories.

Unless explicitly instructed:
- do not edit files under `build/`, `.gradle/`, or other build output directories
- do not modify generated sources produced by Gradle or annotation processors
- make changes in the original source or build configuration that generates those files instead
