package dev.interfacehotkeyguard;

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
public class InterfaceHotkeyGuardPlugin extends Plugin
{
	// Migrate saved preferences from the plugin's original config group.
	private static final String LEGACY_CONFIG_GROUP = "bankinventorytoggleguard";
	private static final String[] CONFIG_KEYS = {
		"protectBank", "protectCollectionLog", "protectSettings",
		"suppressF1", "suppressF2", "suppressF3", "suppressF4",
		"suppressF5", "suppressF6", "suppressF7", "suppressF8",
		"suppressF9", "suppressF10", "suppressF11", "suppressF12"
	};

	@Inject
	private Client client;

	@Inject
	private ClientThread clientThread;

	@Inject
	private KeyManager keyManager;

	@Inject
	private ConfigManager configManager;

	@Inject
	private InterfaceHotkeyGuardConfig config;

	private final InterfaceState interfaceState = new InterfaceState();

	private final KeyListener inputListener = new KeyListener()
	{
		@Override
		public void keyPressed(KeyEvent event)
		{
			if (interfaceState.shouldSuppress(
				config.protectBank(), config.protectCollectionLog(), config.protectSettings())
				&& FunctionKeyFilter.shouldSuppress(event.getKeyCode(), event.getModifiersEx(), config))
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
	InterfaceHotkeyGuardConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(InterfaceHotkeyGuardConfig.class);
	}

	@Override
	protected void startUp()
	{
		migrateConfiguration();
		interfaceState.beginInitialization();
		keyManager.registerKeyListener(inputListener);
		clientThread.invokeLater(() ->
		{
			interfaceState.initialize(
				client.getWidget(InterfaceID.Bankmain.UNIVERSE) != null,
				client.getWidget(InterfaceID.Collection.FRAME) != null,
				client.getWidget(InterfaceID.Settings.UNIVERSE) != null);
		});
	}

	@Override
	protected void shutDown()
	{
		keyManager.unregisterKeyListener(inputListener);
		interfaceState.reset();
	}

	@Subscribe
	public void onWidgetLoaded(WidgetLoaded event)
	{
		interfaceState.onWidgetLoaded(event.getGroupId());
	}

	@Subscribe
	public void onWidgetClosed(WidgetClosed event)
	{
		interfaceState.onWidgetClosed(event.getGroupId());
	}

	private void migrateConfiguration()
	{
		for (String key : CONFIG_KEYS)
		{
			if (configManager.getConfiguration(InterfaceHotkeyGuardConfig.CONFIG_GROUP, key) != null)
			{
				continue;
			}

			String oldValue = configManager.getConfiguration(LEGACY_CONFIG_GROUP, key);
			if (oldValue != null)
			{
				configManager.setConfiguration(InterfaceHotkeyGuardConfig.CONFIG_GROUP, key, oldValue);
			}
		}
	}
}
