# OutingCo Backend

Spring Boot 4.1 · Spring Modulith 2.1 · Java 21 · Gradle.
One deployable (`ApiApplication`), split into modules verified at build time.

## Modules

Sub-packages of `com.outing.api`, declared in `package-info.java`:

| Module | Depends on |
| --- | --- |
| `gateway` | all modules |
| `authentication`, `client`, `event`, `notification`, `user`, `venue` | none |

## Layout

```
<module>/
  api/        public — ExternalAPI, dto/, event/   @NamedInterface("api")
  internal/   hidden — management/, entities/, repositories/, mapper/
```

## Commands

```bash
./gradlew test
./gradlew bootRun
```
