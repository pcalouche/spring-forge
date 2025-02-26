# Spring Forge

<!-- TOC -->
* [Spring Forge](#spring-forge)
  * [What is it and why should I care?](#what-is-it-and-why-should-i-care)
  * [Structure](#structure)
  * [Usage](#usage)
  * [Preconfigured Maven Plugins TODO](#preconfigured-maven-plugins-todo)
  * [Makefile](#makefile)
  * [Spring Forge Autoconfiguration](#spring-forge-autoconfiguration)
    * [Common Controller Advice AutoConfiguration](#common-controller-advice-autoconfiguration)
    * [Logging Auto Configuration](#logging-auto-configuration)
  * [The Spring Forge Libraries](#the-spring-forge-libraries)
    * [logging](#logging)
    * [logging-test](#logging-test)
    * [exceptions](#exceptions)
  * [Supporting Multiple Spring Boot Versions](#supporting-multiple-spring-boot-versions)
  * [Odds and Ends](#odds-and-ends)
    * [The .githooks folder](#the-githooks-folder)
    * [The .github folder](#the-github-folder)
    * [The .mvn folder](#the-mvn-folder)

<!-- TOC -->

## What is it and why should I care?

Spring Forge is library template that lets you extend Spring Boot so to speak for your company or personal needs. In my career, I have been tasked with improving Spring Boot application development at the company on more than one occasion.

From my experience, handling dependency and plugin management in one place across teams works best. This is something I find many
developers, including myself for a good while, do not give this as much thought as I think they should. To be fair, it
is something that takes a while to get the hang of.

I also wanted to leverage Spring Boot's custom autoconfiguration feature, so I could handle cross-cutting concerns (e.g.
logging, error handling, observability, shared application configuration, etc.) across all applications. Bundling this
autoconfiguration in custom Spring Boot starters saves me time and avoids multiple teams building this functionality in
different ways.

In summary, here are the main reasons I think this project may interest someone:

- You want Maven dependency and plugin management to be easy, consolidated, and predictable for you or your team
- You want to create custom Spring Boot autoconfiguration and starters to handle cross-cutting concerns for your Spring
  Boot applications
- You want to scaffold new Spring Boot applications that are production ready in a quick and consistent manner, so you can get down to coding the business logic
- You want to do all the above while supporting multiple Spring Boot versions

Feel to use this template and customize it for your needs.

## Structure

Spring Forge consists of the following modules

|                       Module                        |                                             Description                                             | Spring Forge Dependabot Staging Branch |    Spring Forge Maven Group Name    |
|-----------------------------------------------------|-----------------------------------------------------------------------------------------------------|----------------------------------------|-------------------------------------|
| [Spring Forge Starter POM](pom.xml)                 | The root POM that is intended to be used as the parent POM in applications or libraries.            | main-dependabot-staging                | dev.pcalouche.springforge.spring35x |
| [autoconfigure](autoconfigure)                      | This module contains custom Spring Boot autoconfiguration and its supporting classes.               | spring34x-dependabot-staging           | dev.pcalouche.springforge.spring34x |
| [libs](libs)                                        | Contains libraries to support the autoconfiguration.                                                |                                        |                                     |
| [spring-boot-starters-module](spring-boot-starters) | Custom Spring Boot Starters that applications or libraries can use to active Spring Forge features. |                                        |                                     |

## Usage

These setup instructions assume you are using Maven, but Spring Forge will work with Gradle as well.

Add the following to your application's POM to use Spring Forge.

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>

  <parent>
    <!-- 
      The last part of the package name represents the Spring Boot version used by Spring Forge. For instance, spring34x maps to Spring Boot 3.4.x and spring33x maps to Spring Boot 3.3.x.
    -->
    <groupId>dev.pcalouche.springforge.spring34x</groupId>
    <artifactId>spring-forge-starter-parent</artifactId>
    <version>1.0.0-SNAPSHOT</version>
    <relativePath/>
  </parent>

  <groupId>dev.pcalouche</groupId>
  <artifactId>spring-forge-service-template</artifactId>
  <version>1.0.0-SNAPSHOT</version>

  <!-- Add the following repository to your application's pom.xml -->
  <repositories>
    <repository>
      <snapshots>
        <enabled>true</enabled>
      </snapshots>
      <id>github-pcalouche-spring-forge</id>
      <url>https://maven.pkg.github.com/pcalouche/spring-forge</url>
    </repository>
  </repositories>

  <dependencies>
    <!-- Add the Spring Forge Starters and any dependencies you want to use. -->
    <dependency>
      <groupId>dev.pcalouche.springforge.spring34x</groupId>
      <artifactId>pcalouche-spring-boot-starter-app</artifactId>
    </dependency>
    <dependency>
      <groupId>dev.pcalouche.springforge.spring34x</groupId>
      <artifactId>pcalouche-spring-boot-starter-test</artifactId>
      <scope>test</scope>
    </dependency>
  </dependencies>
</project>
```

Add a server reference in your Maven `settings.xml` to get GitHub repository. GitHub hosted packages require a GitHub username and GitHub token to access them even for read only access.

```xml
<?xml version="1.0" encoding="UTF-8"?>
<settings
        xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.1.0 http://maven.apache.org/xsd/settings-1.1.0.xsd"
        xmlns="http://maven.apache.org/SETTINGS/1.1.0"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">

  <servers>
    <!-- Any other Maven servers would go here -->
    <server>
      <id>github-pcalouche-spring-forge</id>
      <username>your_github_username</username>
      <password>your_github_token</password>
    </server>
  </servers>
</settings>
```

ℹ️ See the [Spring Forge Template](https://github.com/pcalouche/spring-forge-service-template) for a complete example.

## Preconfigured Maven Plugins TODO

These plugins are preconfigured and will be inherited by default by any application or library that used the Spring
Forge Starter POM set as its parent. Modify as needed.

|       Plugin       |                              Purpose                              |           Skip Flag            |
|--------------------|-------------------------------------------------------------------|--------------------------------|
| Spotless           | Used for some aspects of code formatting                          | `spotless.check.skip`          |
| Spring Java Format | Used for some aspects of code formatting                          | `spring-javaformat.skip`       |
| Git Build Hook     | Used to process Git Hooks during the Maven lifecycle              | N/A                            |
| Maven Surefire     | Used for running unit tests                                       | `skipTests` or `skipUnitTests` |
| Maven Failsafe     | Used for running integration tests                                | `skipTests` or `skipITs`       |
| Maven Enforcer     | Used to enforce Maven constraints and detect dependency conflicts | `enforcer.skip`                |
| JaCoCo             | Java Code Coverage reporting tool                                 | `jacoco.skip`                  |

These plugins are pre-configured, but are not automatically inherited. Modify as needed.

|    Plugin    |                 Purpose                  |
|--------------|------------------------------------------|
| Maven Deploy | Used to deploy to Maven                  |
| Spring Boot  | Used to package Spring Boot applications |

## Makefile

The [Makefile](Makefile) in this project has several targets to make Spring Forge development easier. Feel free to
modify or discard.

## Spring Forge Autoconfiguration

This section describes the available Spring Forge custom autoconfiguration.

### Naming convention

The naming conventions for the starters is `<company/owner>-spring-boot-starter-<function>`.

### Common Controller Advice AutoConfiguration

This autoconfiguration is enabled by default if the `pcalouche-spring-boot-starter-app` is on the classpath. It applies
the Spring Controller Advice found
in [CommonControllerAdvice.java](autoconfigure/src/main/java/dev/pcalouche/springforge/autoconfigure/controlleradvice/CommonControllerAdvice.java).
It can be disabled in an application by setting `springforge.common-controller-advice.enabled=false`. It's methods are
also protected, so an application is free to extend it to modify the existing behavior.

### Logging Auto Configuration

This autoconfiguration is enabled by default if the `pcalouche-spring-boot-starter-app` is on the classpath. The
`pcalouche-spring-boot-starter-app` includes `pcalouche-spring-boot-starter-logging` which bundles . It's only
configurable behavior is setting `springforge.logging.json-format=true` to enable logging in JSON format. This is useful
for consumption by various monitoring tools.

## The Spring Forge Libraries

### logging

This library contains a [logback-spring.xml](libs/logging/src/main/resources/logback-spring.xml) configuration file to
standardizing application logging across applications.

### logging-test

This library contains a [logback-test.xml](libs/logging-test/src/main/resources/logback-test.xml) configuration file to
standardizing test logging across applications.

### exceptions

This library contains common exception classes that can be used across applications.

## Supporting Multiple Spring Boot Versions

Spring Forge uses the following branching strategy in order to support multiple Spring Forge versions that use different versions of Spring Boot.

| Spring Boot Version | Spring Forge Release Branch | Spring Forge Dependabot Staging Branch |     Spring Forge Maven Group Name     |
|---------------------|-----------------------------|----------------------------------------|---------------------------------------|
| `3.5.x`             | `main`                      | `dependabot-staging-main`              | `dev.pcalouche.springforge.spring35x` |
| `3.4.x`             | `spring34x-release`         | `dependabot-staging-spring34xq`        | `dev.pcalouche.springforge.spring34x` |

The `main` branch will contain code for the most recent Spring Boot version supported by Spring Forge. How many other release branches depends on what versions of Spring Boot you want Spring Forge to work with. When a release branch is no longer needed it, it can be marked as readonly and have its dependabot configuration removed.

Each release branch has a corresponding Dependabot Staging Branch that groups Dependabot PRs. I have found this strategy works well for the following reasons:
1. If the Spring Forge Starter POM manages many dependencies Dependabot PRs can get numerous
2. I have found that testing all the accumulated Dependabot updates at once on a regular basis (monthly, quarterly, etc.) is better than testing them individually.

The Dependabot staging branches should be periodically synced with their release branches.

See Spring Forge's [dependabot.yml](.github/dependabot.yml) for how this can be implemented.

## Odds and Ends

Below are some miscellaneous some odds and ends to be aware of.

### The .githooks folder

The standard folder for `.githooks`. There is one pre-commit hook that applies formatting when a commit is done.

### The .github folder

The standard folder for GitHub configuration. Spring Forge's `.github` folder consists of:

- [workflows/continuous-integration.yml](.github/workflows/continuous-integration.yml) - Workflow for continuous integration
- [workflows/continuous-delivery.yml](.github/workflows/continuous-delivery.yml) - Workflow for continuous delivery
- [CODEOWNERS](.github/CODEOWNERS) - A https://docs.github.com/en/repositories/managing-your-repositorys-settings-and-features/customizing-your-repository/about-code-owners configuration
- [dependabot.yml](.github/dependabot.yml) - A https://docs.github.com/en/code-security/getting-started/dependabot-quickstart-guide configuration

### The .mvn folder

The [.mvn](.mvn) folder contains a few things of interest:

- [maven.config](.mvn/maven.config) - some default Maven settings for Spring Forge
- [settings.xml](.mvn/settings.xml) - a Maven `settings.xml` that configures Maven in CI/CD

