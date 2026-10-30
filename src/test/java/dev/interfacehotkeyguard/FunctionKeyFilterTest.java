package dev.interfacehotkeyguard;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FunctionKeyFilterTest
{
	@Test
	public void selectsConfiguredFunctionKeys()
	{
		InterfaceHotkeyGuardConfig config = new InterfaceHotkeyGuardConfig() {};
		int[] functionKeys = {
			KeyEvent.VK_F1, KeyEvent.VK_F2, KeyEvent.VK_F3, KeyEvent.VK_F4,
			KeyEvent.VK_F5, KeyEvent.VK_F6, KeyEvent.VK_F7, KeyEvent.VK_F8,
			KeyEvent.VK_F9, KeyEvent.VK_F10, KeyEvent.VK_F11, KeyEvent.VK_F12
		};

		for (int keyCode : functionKeys)
		{
			assertTrue("Expected F-key to be selected: " + keyCode,
				FunctionKeyFilter.shouldSuppress(keyCode, 0, config));
		}

		assertFalse(FunctionKeyFilter.shouldSuppress(KeyEvent.VK_A, 0, config));
	}

	@Test
	public void leavesUnselectedFunctionKeyAlone()
	{
		InterfaceHotkeyGuardConfig config = new InterfaceHotkeyGuardConfig()
		{
			@Override
			public boolean suppressF2()
			{
				return false;
			}
		};

		assertFalse(FunctionKeyFilter.shouldSuppress(KeyEvent.VK_F2, 0, config));
	}

	@Test
	public void allowsModifiedFunctionKeys()
	{
		InterfaceHotkeyGuardConfig config = new InterfaceHotkeyGuardConfig() {};
		int[] modifiers = {
			InputEvent.SHIFT_DOWN_MASK,
			InputEvent.CTRL_DOWN_MASK,
			InputEvent.ALT_DOWN_MASK,
			InputEvent.META_DOWN_MASK,
			InputEvent.ALT_GRAPH_DOWN_MASK,
			InputEvent.CTRL_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK
		};

		for (int modifier : modifiers)
		{
			assertFalse("Expected modifier combination to pass through: " + modifier,
				FunctionKeyFilter.shouldSuppress(KeyEvent.VK_F2, modifier, config));
		}
	}
}
