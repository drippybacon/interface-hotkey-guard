package dev.interfacehotkeyguard;

import net.runelite.api.gameval.InterfaceID;

final class InterfaceState
{
	private static final int BANK_GROUP_ID = InterfaceID.BANKMAIN;
	private static final int COLLECTION_LOG_GROUP_ID = InterfaceID.COLLECTION;
	private static final int SETTINGS_GROUP_ID = InterfaceID.SETTINGS;

	private volatile boolean initialized;
	private volatile boolean bankOpen;
	private volatile boolean collectionLogOpen;
	private volatile boolean settingsOpen;

	void beginInitialization()
	{
		bankOpen = false;
		collectionLogOpen = false;
		settingsOpen = false;
		initialized = false;
	}

	void initialize(boolean bankOpen, boolean collectionLogOpen, boolean settingsOpen)
	{
		this.bankOpen = bankOpen;
		this.collectionLogOpen = collectionLogOpen;
		this.settingsOpen = settingsOpen;
		initialized = true;
	}

	void reset()
	{
		beginInitialization();
	}

	void onWidgetLoaded(int groupId)
	{
		if (groupId == BANK_GROUP_ID)
		{
			bankOpen = true;
		}
		else if (groupId == COLLECTION_LOG_GROUP_ID)
		{
			collectionLogOpen = true;
		}
		else if (groupId == SETTINGS_GROUP_ID)
		{
			settingsOpen = true;
		}
	}

	void onWidgetClosed(int groupId)
	{
		if (groupId == BANK_GROUP_ID)
		{
			bankOpen = false;
		}
		else if (groupId == COLLECTION_LOG_GROUP_ID)
		{
			collectionLogOpen = false;
		}
		else if (groupId == SETTINGS_GROUP_ID)
		{
			settingsOpen = false;
		}
	}

	boolean shouldSuppress(boolean protectBank, boolean protectCollectionLog, boolean protectSettings)
	{
		if (!initialized)
		{
			return protectBank || protectCollectionLog || protectSettings;
		}

		return (protectBank && bankOpen)
			|| (protectCollectionLog && collectionLogOpen)
			|| (protectSettings && settingsOpen);
	}
}
