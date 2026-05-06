# Contributing to NumGuessor

Thank you for your interest in contributing to NumGuessor! Whether it is a bug fix, a new feature, or a documentation improvement, all contributions are welcome.

---

## Getting Started

**1. Fork the repository**

Click the **Fork** button at the top right of the repo page on GitHub.

**2. Clone your fork**

```bash
git clone https://github.com/YOUR-USERNAME/NumGuessor.git
cd NumGuessor
```

**3. Create a branch**

Always work on a new branch, never directly on `main`:

```bash
git checkout -b type/short-description
```

Branch naming examples:

| Type | Example |
|---|---|
| Bug fix | `fix/out-of-range-check` |
| New feature | `feat/hint-system` |
| Documentation | `docs/update-readme` |
| Refactor | `refactor/extract-helpers` |

---

## Making Changes

- Keep changes focused. One branch per fix or feature
- Follow the existing code style (naming conventions, indentation, comments)
- Test your changes manually before submitting
- Update comments or documentation if your change affects them

---

## Commit Message Format

Use clear, consistent commit messages:

```
type: short description in present tense
```

| Type | When to use |
|---|---|
| `feat` | Adding a new feature |
| `fix` | Fixing a bug |
| `refactor` | Code restructuring with no behaviour change |
| `docs` | Documentation changes only |
| `chore` | Build process, `.gitignore`, tooling |

**Examples:**
```
feat: add hint system with warm/cold feedback
fix: correct out-of-range check to exclude 0
docs: update README with build instructions
refactor: extract shared guessing logic into playRound()
```

---

## Submitting a Pull Request

**1. Push your branch**
```bash
git push origin feat/your-feature-name
```

**2. Open a Pull Request on GitHub**

Go to the original NumGuessor repo and click **"Compare & pull request"**.

**3. Fill in the PR description**

Describe what your change does and why. If it fixes a bug, reference the issue:
```
Fixes #12 - corrected range validation to exclude 0 as a valid guess
```

---

## Versioning

NumGuessor follows [Semantic Versioning](https://semver.org/):

```
MAJOR.MINOR.PATCH
```

| Change type | Version bump |
|---|---|
| Bug fixes, typos, grammar | `PATCH` → e.g. `1.1.1` |
| New features | `MINOR` → e.g. `1.2.0` |
| Breaking changes or full rewrite | `MAJOR` → e.g. `2.0.0` |

You do not need to update the version yourself. That is handled by the maintainer at release time.

---

## Planned Features (Good First Contributions)

These are features already on the roadmap. Great starting points if you are looking for something to work on:

- [ ] High score tracking saved to a file
- [ ] Score ranking based on attempts (e.g. *God Mode*, *Close Call*)
- [ ] Difficulty settings (adjustable range and attempt limits)
- [ ] Hint system (*"You're getting warmer!"*)
- [ ] In-game timer
- [ ] Colored terminal output

---

## Questions

If you are unsure about anything, open a [GitHub Issue](../../issues) before starting work. It is always better to discuss first than to spend time on something that might not be accepted.