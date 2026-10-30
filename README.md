# Interface Hotkey Guard

A RuneLite plugin that suppresses selected hotkeys while selected interfaces are open.

## Settings

Options are grouped into **Interfaces** and **Hotkeys**. Choose Bank, Collection Log, and Settings independently, then select which F1–F12 keys to suppress. All three interfaces and all twelve F-keys are enabled by default.

Only unmodified F-key presses are suppressed; Ctrl, Alt, Shift, Meta, and AltGr combinations pass through. RuneLite dispatches keypresses to listeners in registration order and stops after one consumes the event. An earlier listener may already have acted, while later listeners will not receive a suppressed key. Check for conflicts with other plugins using the same F-key.

## Development

Requires JDK 17. Run `gradlew.bat run` to launch the developer client or `gradlew.bat jar` to build the plugin JAR.
