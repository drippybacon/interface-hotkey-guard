package dev.interfacehotkeyguard;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public final class InterfaceHotkeyGuardDev
{
	private InterfaceHotkeyGuardDev()
	{
	}

	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(InterfaceHotkeyGuardPlugin.class);
		RuneLite.main(args);
	}
}
