package dev.bankinventorytoggleguard;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public final class BankInventoryToggleGuardDev
{
	private BankInventoryToggleGuardDev()
	{
	}

	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(BankInventoryToggleGuardPlugin.class);
		RuneLite.main(args);
	}
}
