package dev.bankinventorytoggleguard;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.ConfigSection;

@ConfigGroup("bankinventorytoggleguard")
public interface BankInventoryToggleGuardConfig extends Config
{
	@ConfigSection(
		name = "Interfaces",
		description = "Choose which interfaces protect against F-key shortcuts.",
		position = 0
	)
	String interfacesSection = "interfaces";

	@ConfigSection(
		name = "Hotkeys",
		description = "Choose which function keys to suppress.",
		position = 1
	)
	String hotkeysSection = "hotkeys";

	@ConfigItem(
		keyName = "protectBank",
		name = "Protect Bank",
		description = "Suppress selected F-keys while the Bank is open.",
		section = interfacesSection,
		position = 0
	)
	default boolean protectBank()
	{
		return true;
	}

	@ConfigItem(
		keyName = "protectCollectionLog",
		name = "Protect Collection Log",
		description = "Suppress selected F-keys while the Collection Log is open.",
		section = interfacesSection,
		position = 1
	)
	default boolean protectCollectionLog()
	{
		return true;
	}

	@ConfigItem(
		keyName = "protectSettings",
		name = "Protect Settings",
		description = "Suppress selected F-keys while the Settings interface is open.",
		section = interfacesSection,
		position = 2
	)
	default boolean protectSettings()
	{
		return true;
	}

	@ConfigItem(
		keyName = "suppressF1",
		name = "Suppress F1",
		description = "Suppress F1 while a protected interface is open.",
		section = hotkeysSection,
		position = 0
	)
	default boolean suppressF1()
	{
		return true;
	}

	@ConfigItem(
		keyName = "suppressF2",
		name = "Suppress F2",
		description = "Suppress F2 while a protected interface is open.",
		section = hotkeysSection,
		position = 1
	)
	default boolean suppressF2()
	{
		return true;
	}

	@ConfigItem(
		keyName = "suppressF3",
		name = "Suppress F3",
		description = "Suppress F3 while a protected interface is open.",
		section = hotkeysSection,
		position = 2
	)
	default boolean suppressF3()
	{
		return true;
	}

	@ConfigItem(
		keyName = "suppressF4",
		name = "Suppress F4",
		description = "Suppress F4 while a protected interface is open.",
		section = hotkeysSection,
		position = 3
	)
	default boolean suppressF4()
	{
		return true;
	}

	@ConfigItem(
		keyName = "suppressF5",
		name = "Suppress F5",
		description = "Suppress F5 while a protected interface is open.",
		section = hotkeysSection,
		position = 4
	)
	default boolean suppressF5()
	{
		return true;
	}

	@ConfigItem(
		keyName = "suppressF6",
		name = "Suppress F6",
		description = "Suppress F6 while a protected interface is open.",
		section = hotkeysSection,
		position = 5
	)
	default boolean suppressF6()
	{
		return true;
	}

	@ConfigItem(
		keyName = "suppressF7",
		name = "Suppress F7",
		description = "Suppress F7 while a protected interface is open.",
		section = hotkeysSection,
		position = 6
	)
	default boolean suppressF7()
	{
		return true;
	}

	@ConfigItem(
		keyName = "suppressF8",
		name = "Suppress F8",
		description = "Suppress F8 while a protected interface is open.",
		section = hotkeysSection,
		position = 7
	)
	default boolean suppressF8()
	{
		return true;
	}

	@ConfigItem(
		keyName = "suppressF9",
		name = "Suppress F9",
		description = "Suppress F9 while a protected interface is open.",
		section = hotkeysSection,
		position = 8
	)
	default boolean suppressF9()
	{
		return true;
	}

	@ConfigItem(
		keyName = "suppressF10",
		name = "Suppress F10",
		description = "Suppress F10 while a protected interface is open.",
		section = hotkeysSection,
		position = 9
	)
	default boolean suppressF10()
	{
		return true;
	}

	@ConfigItem(
		keyName = "suppressF11",
		name = "Suppress F11",
		description = "Suppress F11 while a protected interface is open.",
		section = hotkeysSection,
		position = 10
	)
	default boolean suppressF11()
	{
		return true;
	}

	@ConfigItem(
		keyName = "suppressF12",
		name = "Suppress F12",
		description = "Suppress F12 while a protected interface is open.",
		section = hotkeysSection,
		position = 11
	)
	default boolean suppressF12()
	{
		return true;
	}

}
