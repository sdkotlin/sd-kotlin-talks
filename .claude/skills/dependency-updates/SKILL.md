---
name: dependency-updates
description: Check for and upgrade project dependencies following the repository's dependency update workflow, including running checks, updating the version catalog, and validating builds.
---

# Dependency Updates Skill

This skill helps you check for, upgrade, and validate dependencies in this Kotlin/Gradle project following the established workflow.

## When to use this skill

Use this skill when:
- The user asks to check for dependency updates
- The user asks to upgrade a specific dependency or dependencies
- The user mentions outdated dependencies or version updates
- The user asks to run dependency checks

## Repository context

This is a tech demonstrator and evaluator for Kotlin, Gradle, and related tooling. The project uses a Gradle version catalog (`libs.versions.toml`) for dependency management.

## Workflow

### 1. Check for dependency updates

Run the dependency update check:

```
./gradlew dependencyUpdates --no-parallel
```

Review the output to identify available updates.

### 2. Update dependencies stepwise

**CRITICAL: Update dependencies ONE AT A TIME, allowing the maintainer to review and commit each update independently.**

When multiple dependency updates are available:

1. **Group and prioritize updates based on compatibility relationships:**
   - **Compiler plugins first**: Update dependencies that contribute Kotlin compiler plugins (KSP, Mokkery, Detekt, etc.) BEFORE updating Kotlin itself
   - **BOM/platform dependencies**: Update BOMs and platform dependencies before their constituent libraries
   - **Core dependencies before dependents**: Update libraries before things that depend on them
   - **Independent libraries**: Update standalone test libraries, utilities, etc. independently

2. **Test compatibility with current versions first:**
   - For compiler plugins and build plugins, check if newer versions work with the CURRENT Kotlin/Gradle version
   - If they do, update them first in independent commits
   - Then update Kotlin/Gradle afterward in separate commits
   - This isolates compatibility issues and makes rollback easier

3. **Update process for each dependency:**
   - Update ONLY ONE dependency version in `libs.versions.toml`
   - Keep version catalog edits consistent with existing style
   - Search the repository for usages of its alias to ensure examples still compile
   - Run validation (step 4) - BOTH `build --rerun-tasks` AND `buildHealth`
   - Report results and **PAUSE** for maintainer review and commit
   - **WAIT for explicit confirmation** before proceeding to the next dependency

4. **Watch for compatibility issues:**
   - Compiler API changes (especially with Kotlin compiler plugins)
   - Breaking changes in test frameworks
   - Gradle compatibility requirements
   - Behavioral changes that affect demonstrations
   - Call out any deprecations, warnings, or required source changes

5. **When to batch updates:**
   - Only batch updates when they MUST be updated together (e.g., a library and its required companion version)
   - Always prefer single-dependency commits when possible
   - If batching, clearly explain WHY they must be updated together

**Example update order:**
1. KSP (compiler plugin - test with current Kotlin first)
2. Mokkery (compiler plugin - test with current Kotlin first)
3. Detekt (if applicable)
4. Kotlin (after all compiler plugins are compatible)
5. Koin (independent library)
6. Kotest (independent test library)
7. Mockito (independent test library)
8. Other independent libraries

After each dependency update, **search the repository for usages of its alias** to ensure examples still compile.

### 3. Modernize examples (when applicable)

When dependency upgrades introduce improved APIs, patterns, or idioms:

- Prefer updating examples to demonstrate the modern approach
- Replace deprecated APIs with their recommended replacements
- Update demonstration code to reflect current best practices where practical
- If both old and new approaches are educational, it is acceptable to keep both examples
- Prefer modernizing examples to demonstrate best practices introduced by the upgraded dependency

### 4. Validation

**CRITICAL: Run BOTH validation tasks for EVERY dependency update.**

After each dependency change, verify the build with:

```
./gradlew build --rerun-tasks
```

Then run:

```
./gradlew buildHealth
```

Both validations must pass before reporting results to the maintainer. This ensures the dependency update doesn't break compilation, tests, or introduce dependency health issues.

### 5. Reporting

When done:
- Explain what changed and why
- Report what validation was run
- Separate follow-up ideas from completed work
- **Do not create Git commits** - stop after changes are validated
- Avoid bundling speculative cleanup into the same change

For dependency upgrades, summarize both the version change and any example modernizations performed because of it.

## Important constraints

### Version catalog
- Do not rename catalog aliases, bundles, or plugin aliases unless explicitly asked
- Maintain consistent formatting and style

### Repository philosophy
- Keep diffs focused and minimal
- Do not perform unrelated refactors
- Preserve existing project structure and naming conventions
- Follow existing patterns in build logic, module organization, and version catalog usage

### Warnings and inspections
Some source files intentionally contain compilation warnings or IDE inspection warnings to demonstrate incorrect patterns or deprecated APIs.

**Do not remove warnings or suppress them** unless the task explicitly requires it.

### Guardrails
- Do not introduce new dependencies without clear justification
- Do not change unrelated versions while performing a targeted upgrade
- Do not remove demonstrator or experimental code unless explicitly asked
- Prefer version-catalog-based dependency management where the project already uses it

### Git workflow
- **Do not run `git commit`, `git push`, or create branches** unless explicitly instructed
- Prepare clean, reviewable working tree changes and stop after validation
- Leave changes for the maintainer to review and commit manually

## If a change could alter the intent of a demonstration

If a change could alter the intent of a demonstration or example, **ask for clarification** rather than modifying it.
