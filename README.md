# NumGuessor

A terminal-based number guessing game written in Java, where the Elder Gods choose a number and you must guess it.

---

## Gameplay

A random number between **1 and 100** is chosen. Your job is to guess it using higher/lower hints before your attempts run out.

### Game Modes

| Mode | Description |
|---|---|
| **Limited Steps** | 1 round of 5 attempts. Guess it or lose. |
| **Unlimited Steps** | Unlimited rounds of 5 attempts each. Keep going until you find it. |

At any guess prompt, type `quit` to return to the main menu.

---

## Download & Install

1. Go to the [Releases](../../releases) page
2. Download the latest `NumGuessor-x.x.x.exe`
3. Run the installer
4. Launch NumGuessor from the Start Menu or Desktop shortcut

> **Requirements:** No Java installation needed. The JRE is bundled with the installer.

---

## Building from Source

**Requirements:**
- JDK 14 or higher
- WiX Toolset v3.x (for building the `.exe` installer)

**Steps:**

```bash
# 1. Clone the repo
git clone https://github.com/briangor/numguessor.git
cd NumGuessor

# 2. Compile
javac NumGuessor.java

# 3. Package into a JAR
jar cfe NumGuessor.jar NumGuessor NumGuessor.class

# 4. Build the installer
jpackage --input . \
         --main-jar NumGuessor.jar \
         --main-class NumGuessor \
         --name NumGuessor \
         --type exe \
         --win-console \
         --icon NumGuessor.ico \
         --app-version 1.1.1 \
         --vendor "0xb13" \
         --description "A number guessing game" \
         --copyright "Copyright 2026 0xb13" \
         --win-shortcut \
         --win-menu \
         --win-menu-group "Games" \
         --win-upgrade-uuid YOUR-UUID-HERE
```

Note: Generate your own **UUID** by running `[guid]::NewGuid()` in PowerShell. Never reuse someone else's UUID.

---

## Version History

| Version | Description |
|---|---|
| `v1.1.1` | Grammar and text corrections |
| `v1.1.0` | Added `quit` command, play-again in unlimited mode, main menu navigation |
| `v1.0.0` | Initial release |

---

## Planned Features

- High score tracking across sessions
- Score ranking system (e.g. 1 attempt = *God Mode*, 5 attempts = *Close Call*)
- Difficulty settings (adjustable range and attempt limits)
- Hint system (*"You're getting warmer!"*)
- In-game timer

---

## Contributing

Contributions are welcome! Please read [CONTRIBUTING.md](CONTRIBUTING.md) before submitting a pull request.

---

## License

This project is licensed under the MIT License.