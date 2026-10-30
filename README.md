# Interface Hotkey Guard

A RuneLite plugin that suppresses selected F-key shortcuts while the Bank, Collection Log, or Settings interface is open. Esc is left alone so it continues to close interfaces normally.

F1-F12 each have a separate checkbox, all enabled by default. Printable characters such as W are unaffected and can still be typed into search fields.

## Configure

After the plugin is loaded, open its configuration. Options are split into **Interfaces** and **Hotkeys** sections. Choose which interfaces to protect and which F-keys to suppress. Bank, Collection Log, and Settings are all protected by default; all twelve F-keys are enabled by default. Custom letter shortcuts are not configurable.

Selected F-keys work normally whenever neither protected interface is open. Esc is never intercepted.

## Build

This project follows RuneLite's official example-plugin layout and targets Java 11 bytecode. JDK 17 or newer is required to run Gradle 9.8.2. The Gradle Wrapper is included, so a separate Gradle installation is not needed. From PowerShell in this directory, run `./gradlew.bat jar`; the first run downloads Gradle and RuneLite dependencies. The plugin jar will be written under `build/libs`.

The official RuneLite example-style developer launcher is included. Run `./gradlew.bat run` to launch a developer-mode RuneLite client with this plugin loaded. This opens the RuneLite application; the launcher does not install the plugin into your regular RuneLite profile.

For a Jagex Account, use the Jagex Accounts button in the developer client's sidebar, choose **Add Jagex Account**, complete the sign-in window, select your character, and click **Play Now**. The standard username/password login form is not the Jagex Account sign-in path. See [RuneLite's Jagex Account instructions](https://github.com/runelite/runelite/wiki/Using-Jagex-Accounts). This project does not access game accounts or send input.

## Source layout

- `src/main/java/dev/bankinventorytoggleguard/BankInventoryToggleGuardConfig.java` contains the per-key F1-F12 checkboxes.
- `src/main/java/dev/bankinventorytoggleguard/BankInventoryToggleGuardPlugin.java` tracks the Bank, Collection Log, and Settings interfaces and suppresses selected shortcuts while an enabled interface is open.
- `runelite-plugin.properties` identifies the plugin to RuneLite's plugin tooling.

This is a local personal plugin project. It has not been submitted to Plugin Hub.




