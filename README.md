# OutingCo

# Contributing Guide

## Commit Convention

```
<type>: <short description>
```

### Types

| Type       | Use when                                |
| ---------- | --------------------------------------- |
| `feat`     | Adding a new feature                    |
| `fix`      | Fixing a bug                            |
| `chore`    | Maintenance, config, tooling            |
| `docs`     | Documentation changes only              |
| `refactor` | Code restructure, no behavior change    |
| `test`     | Adding or updating tests                |
| `style`    | Formatting, whitespace, no logic change |

---

## Branch Naming

```
<type>/<jira-id>-<short-description>
```

### Types

| Type      | Use when             |
| --------- | -------------------- |
| `feature` | Adding a new feature |
| `fix`     | Fixing a bug         |

---

## Pull Request

PR title must include the Jira issue key:

```
[Jira-id] Short Description
```

---

## Flow Summary

```
1. git checkout -b feature/OUTING-115-your-feature
2. git commit -m "feat: OUTING-115 your commit message"
3. Create PR → title: [OUTING-115] Your Feature
```
