@'
# 🎯 Number Guessing Engine v2.6 Pro

A premium console-based **Number Guessing Game** built using **Java**. This application features high-contrast terminal theme accents, real-time feedback loops, dynamic score generation, and smart game difficulty loops.

---

## ✨ Features
* 🎨 **Full Background Accent Colors:** Vibrant UI warnings using full ANSI row injection (`Red` for High, `Yellow` for Low, `Green` for Victory).
* ⚙️ **Dynamic Difficulty System:** Choose between Easy (10 tries + hints), Medium (7 tries), and Hard (5 tries - hardcore mode).
* 💡 **Smart System Hints:** Automated ODD/EVEN calculations triggered during easy/medium modes to aid progression.
* 📦 **Modular Package Structure:** Separated clean architecture split across `game` and `ui` packages.
* 🏁 **Custom Premium Outro:** High-contrast `THANK YOU FOR PLAYING` custom visual ending card.

---

## 🎮 How to Play
1. Launch the application inside any native environment terminal.
2. Select your gameplay difficulty limit layer (1-3).
3. Guess the secret matching number ranging systematically from **1 to 100**.
4. Read the color-coded block warning prompts to readjust your target bounds!

---

## 🛠️ Compilation and Execution

Ensure you have **Java JDK** installed on your workstation setup. Paste these commands into your root project directory terminal:

```bash
# Compile package modules cleanly
javac -d . game/*.java ui/*.java

# Run main application layer
java game.Main
