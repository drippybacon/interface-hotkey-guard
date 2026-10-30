package dev.bankinventorytoggleguard;

import com.google.inject.Provides;
import java.awt.event.KeyEvent;
import javax.inject.Inject;
import net.runelite.api.Client;
import net.runelite.api.events.WidgetClosed;
import net.runelite.api.events.WidgetLoaded;
import net.runelite.api.gameval.InterfaceID;
import net.runelite.client.callback.ClientThread;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.input.KeyListener;
import net.runelite.client.input.KeyManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

@PluginDescriptor(
	name = "Interface Hotkey Guard",
	description = "Suppresses selected hotkeys while certain interfaces are open",
	enabledByDefault = false
)
public class BankInventoryToggleGuardPlugin extends Plugin
{
	private static final int BANK_GROUP_ID = InterfaceID.BANKMAIN;
	private static final int COLLECTION_LOG_GROUP_ID = InterfaceID.COLLECTION;
	private static final int SETTINGS_GROUP_ID = InterfaceID.SETTINGS;

	@Inject
	private Client client;

	@Inject
	private ClientThread clientThread;

	@Inject
	private KeyManager keyManager;

	@Inject
	private BankInventoryToggleGuardConfig config;

	private volatile boolean bankOpen;
	private volatile boolean collectionLogOpen;
	private volatile boolean settingsOpen;

	private final KeyListener inputListener = new KeyListener()
	{
		@Override
		public void keyPressed(KeyEvent event)
		{
			if (isProtectedContext() && isSelectedFunctionKey(event.getKeyCode()))
			{
				event.consume();
			}
		}

		@Override
		public void keyTyped(KeyEvent event)
		{
		}

		@Override
		public void keyReleased(KeyEvent event)
		{
		}

		@Override
		public void focusLost()
		{
		}
	};

	@Provides
	BankInventoryToggleGuardConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(BankInventoryToggleGuardConfig.class);
	}

	@Override
	protected void startUp()
	{
		keyManager.registerKeyListener(inputListener);
		clientThread.invokeLater(() ->
		{
			bankOpen = client.getWidget(InterfaceID.Bankmain.UNIVERSE) != null;
			collectionLogOpen = client.getWidget(InterfaceID.Collection.FRAME) != null;
			settingsOpen = client.getWidget(InterfaceID.Settings.UNIVERSE) != null;
		});
	}

	@Override
	protected void shutDown()
	{
		keyManager.unregisterKeyListener(inputListener);
		bankOpen = false;
		collectionLogOpen = false;
		settingsOpen = false;
	}

	@Subscribe
	public void onWidgetLoaded(WidgetLoaded event)
	{
		if (event.getGroupId() == BANK_GROUP_ID)
		{
			bankOpen = true;
		}
		else if (event.getGroupId() == COLLECTION_LOG_GROUP_ID)
		{
			collectionLogOpen = true;
		}
		else if (event.getGroupId() == SETTINGS_GROUP_ID)
		{
			settingsOpen = true;
		}
	}

	@Subscribe
	public void onWidgetClosed(WidgetClosed event)
	{
		if (event.getGroupId() == BANK_GROUP_ID)
		{
			bankOpen = false;
		}
		else if (event.getGroupId() == COLLECTION_LOG_GROUP_ID)
		{
			collectionLogOpen = false;
		}
		else if (event.getGroupId() == SETTINGS_GROUP_ID)
		{
			settingsOpen = false;
		}
	}

	private boolean isSelectedFunctionKey(int keyCode)
	{
		switch (keyCode)
		{
			case KeyEvent.VK_F1:
				return config.suppressF1();
			case KeyEvent.VK_F2:
				return config.suppressF2();
			case KeyEvent.VK_F3:
				return config.suppressF3();
			case KeyEvent.VK_F4:
				return config.suppressF4();
			case KeyEvent.VK_F5:
				return config.suppressF5();
			case KeyEvent.VK_F6:
				return config.suppressF6();
			case KeyEvent.VK_F7:
				return config.suppressF7();
			case KeyEvent.VK_F8:
				return config.suppressF8();
			case KeyEvent.VK_F9:
				return config.suppressF9();
			case KeyEvent.VK_F10:
				return config.suppressF10();
			case KeyEvent.VK_F11:
				return config.suppressF11();
			case KeyEvent.VK_F12:
				return config.suppressF12();
			default:
				return false;
		}
	}

	private boolean isProtectedContext()
	{
		return (config.protectBank() && bankOpen)
			|| (config.protectCollectionLog() && collectionLogOpen)
			|| (config.protectSettings() && settingsOpen);
	}
}

