package dev.interfacehotkeyguard;

import net.runelite.api.gameval.InterfaceID;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class InterfaceStateTest
{
	@Test
	public void protectsEnabledInterfacesUntilStartupSnapshotArrives()
	{
		InterfaceState state = new InterfaceState();
		state.beginInitialization();

		assertTrue(state.shouldSuppress(true, false, false));
		assertFalse(state.shouldSuppress(false, false, false));

		state.initialize(false, false, false);
		assertFalse(state.shouldSuppress(true, true, true));
	}

	@Test
	public void startupSnapshotRecognizesAlreadyOpenInterfaces()
	{
		InterfaceState state = new InterfaceState();
		state.beginInitialization();
		state.initialize(true, false, false);

		assertTrue(state.shouldSuppress(true, false, false));
		assertFalse(state.shouldSuppress(false, true, true));
	}

	@Test
	public void tracksEachInterfaceOpeningAndClosingIndependently()
	{
		int[] groups = {
			InterfaceID.BANKMAIN,
			InterfaceID.COLLECTION,
			InterfaceID.SETTINGS
		};
		boolean[][] protections = {
			{true, false, false},
			{false, true, false},
			{false, false, true}
		};

		for (int i = 0; i < groups.length; i++)
		{
			InterfaceState state = new InterfaceState();
			state.initialize(false, false, false);
			state.onWidgetLoaded(groups[i]);
			assertTrue("Expected interface group to be protected: " + groups[i],
				state.shouldSuppress(protections[i][0], protections[i][1], protections[i][2]));

			state.onWidgetClosed(groups[i]);
			assertFalse("Expected closed interface group to be unprotected: " + groups[i],
				state.shouldSuppress(protections[i][0], protections[i][1], protections[i][2]));
		}
	}

	@Test
	public void disabledInterfaceProtectionDoesNotSuppressKeys()
	{
		InterfaceState state = new InterfaceState();
		state.initialize(true, true, true);

		assertFalse(state.shouldSuppress(false, false, false));
		assertTrue(state.shouldSuppress(true, false, false));
		assertTrue(state.shouldSuppress(false, true, false));
		assertTrue(state.shouldSuppress(false, false, true));
	}

	@Test
	public void resetClosesAllInterfacesAndDisablesSuppression()
	{
		InterfaceState state = new InterfaceState();
		state.initialize(true, true, true);

		state.reset();

		assertFalse(state.shouldSuppress(true, true, true));
	}
}
