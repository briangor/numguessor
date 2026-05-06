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

Go to the [Releases](../../releases) page and download the installer for your platform:

| Platform | File |
|---|---|
| **Windows** | `NumGuessor-x.x.x.exe` |
| **Linux (Debian/Ubuntu)** | `NumGuessor_x.x.x.deb` |
| **macOS** | `NumGuessor-x.x.x.dmg` |

**Windows**
1. Run `NumGuessor-x.x.x.exe`
2. Follow the installer prompts
3. Launch NumGuessor from the Start Menu or Desktop shortcut

**Linux**
```bash
sudo dpkg -i NumGuessor_x.x.x.deb
```
Then launch NumGuessor from your application menu or run 'numguessor` in the terminal.

**macOS**
1. Open `NumGuessor-x.x.x.dmg`
2. Drag NumGuessor into your Applications folder
3. Launch from Applications or Spotlight

> **Requirements:** No Java installation needed. The JRE is bundled with the installer on all platforms.

---

## Building from Source

**Requirements:**
- JDK 17 or higher
- WiX Toolset v3.x (Windows only, required by jpackage to build `.exe`)

> You can only build an installer for the OS you are currenty on. The [release workflow](.github/workflows/release.yml) handles all three platforms automatically via GitHub Actions.

**Steps (all platforms):**

```bash
# 1. Clone the repo
git clone https://github.com/briangor/numguessor.git
cd NumGuessor

# 2. Compile
javac src/NumGuessor.java -d out/

# 3. Package into a JAR
jar cfe NumGuessor.jar NumGuessor -C out/ .
```

**4. Build the installer for your platform**

**Windows**
```cmd
jpackage --input . ^
         --main-jar NumGuessor.jar ^
         --main-class NumGuessor ^
         --name NumGuessor ^
         --type exe ^
         --win-console ^
         --icon assets/icons/NumGuessor.ico ^
         --app-version 1.2.0 ^
         --vendor "0xb13" ^
         --description "A number guessing game" ^
         --copyright "Copyright 2026 0xb13" ^
         --win-shortcut ^
         --win-menu ^
         --win-menu-group "Games" ^
         --win-upgrade-uuid YOUR-UUID-HERE
```

> Generate your own **UUID** by running `[guid]::NewGuid()` in PowerShell. Never reuse someone else's UUID.

**Linux**
```bash
jpackage --input . \
         --main-jar NumGuessor.jar \
         --main-class NumGuessor \
         --name NumGuessor \
         --type deb \
         --icon assets/icons/NumGuessor.png \
         --app-version 1.2.0 \
         --vendor "0xb13" \
         --description "A number guessing game" \
         --copyright "Copyright 2026 0xb13" \
         --linux-shortcut \
         --linux-menu-group "Games" \
         --linux-app-category "Game"
```

**macOS**
```bash
jpackage --input . \
         --main-jar NumGuessor.jar \
         --main-class NumGuessor \
         --name NumGuessor \
         --type dmg \
         --icon assets/icons/NumGuessor.icns \
         --app-version 1.2.0 \
         --vendor "0xb13" \
         --description "A number guessing game" \
         --copyright "Copyright 2026 0xb13" \
         --mac-package-name "NumGuessor" \
         --mac-package-identifier "com.0xb13.numguessor"   
```

---

## Version History

| Version | Description |
|---|---|   
| `v1.2.0` | Add Linux and macOS installer support |
| `v1.1.1` | Grammar and text corrections |
| `v1.1.0` | Add `quit` command, play-again in unlimited mode, main menu navigation |
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