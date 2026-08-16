# OutingCo Backend

Spring Boot 4.1 · Spring Modulith 2.1 · Java 21 · Gradle.
One deployable (`ApiApplication`), split into modules verified at build time.

## Modules

Sub-packages of `com.outing.api`, declared in `package-info.java`:

| Module | Depends on |
| --- | --- |
| `gateway` | all modules |
| `authentication`, `client`, `event`, `notification`, `user`, `venue` | none |

## Structure

```
backend/
├── build.gradle
├── gradle/
└── src
    ├── main
    │   ├── java/com/outing/api
    │   │   ├── ApiApplication.java
    │   │   ├── gateway/
    │   │   │   └── package-info.java
    │   │   └── module-name/
    │   │       ├── package-info.java 
    │   │       ├── api (public)/
    │   │       │   ├── ClientExternalAPI.java
    │   │       │   ├── dto/
    │   │       │   └── event/
    │   │       │   └── package-info.java
    │   │       └── internal/
    │   │           ├── api/ClientInternalAPI.java
    │   │           ├── management/ClientManagement.java
    │   │           ├── entities/
    │   │           ├── repositories/
    │   │           └── mapper/
    │   └── resources/application.yaml
    └── test/java/com/outing/api
        ├── ApiApplicationTests.java
        └── ModularityTests.java
```

## Commands

```bash
./gradlew test
./gradlew bootRun
```
