package com.zarkonnen.cyberpunk;

import com.zarkonnen.catengine.Condition;
import com.zarkonnen.catengine.Draw;
import com.zarkonnen.catengine.Fount;
import com.zarkonnen.catengine.Frame;
import com.zarkonnen.catengine.Game;
import com.zarkonnen.catengine.Hooks;
import com.zarkonnen.catengine.Input;
import com.zarkonnen.catengine.SlickEngine;
import com.zarkonnen.catengine.util.Clr;
import com.zarkonnen.catengine.util.ScreenMode;

public class Cyberpunk implements Game {
	public static final String ALPHABET = " qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM1234567890-=+_!?<>,.;:\"'@£$%^&*()[]{}|\\~/±";
	public static final Fount OCRA = new Fount("OCRA12", 11, 18, 7, 16, ALPHABET);
	public static final Fount COURIER = new Fount("Courier12", 10, 15, 7, 15, ALPHABET);
	
	public static void main(String[] args) {
		SlickEngine e = new SlickEngine("Cyberpunk", "/com/zarkonnen/cyberpunk/images/", "com/zarkonnen/cyberpunk/sounds/", 60);
		e.setup(new Cyberpunk());
		e.runUntil(Condition.ALWAYS);
	}
	
	private boolean modeSet = false;
	private Screen currentScreen = new WorldScreen(new GameState(666, 20, 20, 10));
	
	private Hooks hooks = null;

	@Override
	public void input(Input in) {
		if (!modeSet) {
			ScreenMode best = null;
			for (ScreenMode m : in.modes()) {
				if (best == null || (m.width * m.height > best.width * best.height && m.fullscreen)) {
					best = m;
				}
			}
			if (best != null) {
				in.setMode(best);
			}
			modeSet = true;
		}
		currentScreen.input(in);
		if (hooks != null) {
			hooks.hit(in);
		}
	}

	@Override
	public void render(Frame f) {
		ScreenMode sm = f.mode();
		Draw d = new Draw(f);
		currentScreen.render(d, sm);
		hooks = d.getHooks();
	}
}
