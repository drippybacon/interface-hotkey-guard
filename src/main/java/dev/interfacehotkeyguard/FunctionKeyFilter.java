package dev.interfacehotkeyguard;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

final class FunctionKeyFilter
{
	private static final int MODIFIER_MASK = InputEvent.SHIFT_DOWN_MASK
		| InputEvent.CTRL_DOWN_MASK
		| InputEvent.ALT_DOWN_MASK
		| InputEvent.META_DOWN_MASK
		| InputEvent.ALT_GRAPH_DOWN_MASK;

	private FunctionKeyFilter()
	{
	}

	static boolean shouldSuppress(int keyCode, int modifiers, InterfaceHotkeyGuardConfig config)
	{
		if ((modifiers & MODIFIER_MASK) != 0)
		{
			return false;
		}

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
}
