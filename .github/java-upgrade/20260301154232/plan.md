# Upgrade Plan: my-app (20260301154232)

- **Generated**: 2026-03-01 15:42:32
- **HEAD Branch**: main
- **HEAD Commit ID**: 1396c25f979a87aafafc2c3bca3e4b0b9c2e3e85

## Available Tools

**JDKs**
- JDK 21.0.8: C:\Users\juanm\AppData\Local\Programs\Eclipse Adoptium\jdk-21.0.8.9-hotspot (target version, already available)

**Build Tools**
- Maven 3.9.12: C:\Users\juanm\.maven\maven-3.9.12\bin (installed in Step 1)
- Maven Wrapper: Not present

## Guidelines

> Note: You can add any specific guidelines or constraints for the upgrade process here if needed, bullet points are preferred.

## Options

- Working branch: appmod/java-upgrade-<SESSION_ID> <!-- user specified, NEVER remove it -->
- Run tests before and after the upgrade: true <!-- user specified, NEVER remove it -->

## Upgrade Goals

- Upgrade Java from 11 to 21 LTS

### Technology Stack

<!--
  Table of core dependencies and their compatibility with upgrade goals.
  IMPORTANT: Analyze ALL modules in multi-module projects, not just the root module.
  Only include: direct dependencies + those critical for upgrade compatibility.
  CRITICAL: Identify and clearly flag EOL (End of Life) dependencies - these pose security risks and must be upgraded.

  Columns:
  - Technology/Dependency: Name of the dependency (mark EOL dependencies with "⚠️ EOL" suffix)
  - Current: Version currently in use
  - Min Compatible Version: Minimum version that works with upgrade goals (or N/A if replaced)
  - Why Incompatible: Explanation of incompatibility, or "-" if already compatible. For EOL deps, mention security/support concerns.

  SAMPLE:
  | Technology/Dependency    | Current | Min Compatible | Why Incompatible                               |
  | ------------------------ | ------- | -------------- | ---------------------------------------------- |
  | Java                     | 8       | 21             | User requested                                 |
  | Spring Boot              | 2.5.0   | 3.2.0          | User requested                                 |
  | Spring Framework         | 5.3.x   | 6.1.x          | Spring Boot 3.2 requires Spring Framework 6.1+ |
  | Hibernate                | 5.4.x   | 6.1.x          | Spring Boot 3.x requires Hibernate 6+          |
  | javax.servlet ⚠️ EOL     | 4.0     | N/A            | Replaced by jakarta.servlet in Spring Boot 3.x; javax namespace EOL |
  | Log4j ⚠️ EOL             | 1.2.17  | N/A            | EOL since 2015, critical security vulnerabilities; replace with Logback/Log4j2 |
  | DWR ⚠️ EOL             | 3.0.1.rc  | N/A            | EOL since 2017, no longer maintained; consider replacing with modern alternatives |
  | Lombok                   | 1.18.20 | 1.18.20        | -                                              |
-->

| Technology/Dependency | Current | Min Compatible | Why Incompatible |
| --------------------- | ------- | -------------- | ---------------- |
| Java JDK | 11 | 21 | User requested upgrade to Java 21 LTS |
| JUnit | 4.13.2 | 4.13.2 | Compatible with Java 21 (no upgrade required) |
| Maven Compiler Plugin | (inherited) | 3.11.0+ | Required for Java 21 compilation support |
| Maven Surefire Plugin | 3.0.0-M6 | 3.2.5 | Milestone version, stable release recommended for Java 21 |

### Derived Upgrades

- **Maven Compiler Plugin** to 3.13.0: Required for Java 21 compilation support and compatibility
- **Maven Surefire Plugin** to 3.2.5: Upgrade from milestone to stable release for reliable Java 21 test execution

## Upgrade Steps

- **Step 1: Setup Environment**
  - **Rationale**: Maven was not installed on the system. Need to ensure Maven is available and configured in PATH for subsequent build operations.
  - **Changes to Make**:
    - [x] Install Maven 3.9.12 (already completed)
    - [x] Verify JDK 21 is available (already present)
    - [ ] Configure Maven in PATH for current session
  - **Verification**:
    - Command: `mvn --version`
    - Expected: Maven 3.9.12 and JDK 21.0.8 displayed

---

- **Step 2: Setup Baseline**
  - **Rationale**: Establish pre-upgrade compile and test results to measure upgrade success against.
  - **Changes to Make**:
    - [ ] Run baseline compilation with current JDK 21 (project configured for Java 11 but JDK 21 available)
    - [ ] Run baseline tests to establish test pass rate
    - [ ] Document current test results
  - **Verification**:
    - Command: `mvn clean test-compile` and `mvn clean test`
    - JDK: C:\Users\juanm\AppData\Local\Programs\Eclipse Adoptium\jdk-21.0.8.9-hotspot
    - Expected: Document compilation SUCCESS/FAILURE and test pass rate

---

- **Step 3: Update Maven Plugins**
  - **Rationale**: Maven Compiler Plugin needs explicit configuration for Java 21 support. Surefire Plugin should be upgraded to latest stable version.
  - **Changes to Make**:
    - [ ] Add explicit maven-compiler-plugin configuration version 3.13.0 with source/target still at 11
    - [ ] Update maven-surefire-plugin from 3.0.0-M6 to 3.2.5
    - [ ] Ensure project still compiles with Java 11 settings
  - **Verification**:
    - Command: `mvn clean test-compile`
    - JDK: C:\Users\juanm\AppData\Local\Programs\Eclipse Adoptium\jdk-21.0.8.9-hotspot
    - Expected: Compilation SUCCESS with updated plugins

---

- **Step 4: Upgrade to Java 21**
  - **Rationale**: Update all Java version properties to 21 to complete the upgrade goal.
  - **Changes to Make**:
    - [ ] Update `jdk.version` property from 11 to 21
    - [ ] Update `maven.compiler.source` from 11 to 21
    - [ ] Update `maven.compiler.target` from 11 to 21
    - [ ] Compile both main and test code
  - **Verification**:
    - Command: `mvn clean test-compile`
    - JDK: C:\Users\juanm\AppData\Local\Programs\Eclipse Adoptium\jdk-21.0.8.9-hotspot
    - Expected: Both main and test code compile successfully with Java 21

---

- **Step 5: Final Validation**
  - **Rationale**: Verify all upgrade goals met, project compiles successfully, and all tests pass.
  - **Changes to Make**:
    - [ ] Verify all target versions (Java 21) in pom.xml
    - [ ] Resolve ALL TODOs and temporary workarounds from previous steps
    - [ ] Clean rebuild with JDK 21
    - [ ] Run full test suite and fix ALL test failures iteratively until 100% pass
  - **Verification**:
    - Command: `mvn clean test`
    - JDK: C:\Users\juanm\AppData\Local\Programs\Eclipse Adoptium\jdk-21.0.8.9-hotspot
    - Expected: Compilation SUCCESS + 100% tests pass

## Key Challenges

- **Direct Java 11 to 21 Upgrade**
  - **Challenge**: Upgrading from Java 11 to 21 spans 5 LTS releases (11 → 17 → 21), introducing potential API changes, deprecations, and removed features.
  - **Strategy**: Direct upgrade is feasible for this project due to minimal dependencies (only JUnit 4). JUnit 4.13.2 is fully compatible with Java 21. Monitor for any deprecation warnings during compilation.

- **Maven Plugin Compatibility**
  - **Challenge**: Default inherited Maven Compiler Plugin version may not support Java 21 features.
  - **Strategy**: Explicitly configure maven-compiler-plugin version 3.13.0 which has full Java 21 support. Update Surefire from milestone to stable release.

- **Test Code Compatibility**
  - **Challenge**: Test code may use reflection or internal APIs that changed between Java 11 and 21.
  - **Strategy**: Compile both main and test code in separate steps. Address any test compilation issues before running tests in Final Validation.
