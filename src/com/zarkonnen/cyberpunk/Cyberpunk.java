package com.zarkonnen.cyberpunk;

import com.zarkonnen.catengine.Condition;
import com.zarkonnen.catengine.Draw;
import com.zarkonnen.catengine.Fount;
import com.zarkonnen.catengine.Frame;
import com.zarkonnen.catengine.Game;
import com.zarkonnen.catengine.Hooks;
import com.zarkonnen.catengine.Input;
import com.zarkonnen.catengine.Java2DEngine;
import com.zarkonnen.catengine.util.Pt;
import com.zarkonnen.catengine.util.Rect;
import com.zarkonnen.catengine.util.ScreenMode;
import javax.swing.JOptionPane;

public class Cyberpunk implements Game {
	public static final String ALPHABET = " qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM1234567890-=+_!?<>,.;:\"'@£$%^&*()[]{}|\\~/±";
	public static final Fount OCRA = new Fount("OCRA12", 11, 18, 7, 16, ALPHABET);
	public static final Fount COURIER = new Fount("Courier12", 10, 15, 7, 15, ALPHABET);
	
	public static void main(String[] args) {
		Java2DEngine e = new Java2DEngine("Cyberpunk", "/com/zarkonnen/cyberpunk/images/", "com/zarkonnen/cyberpunk/sounds/", 60);
		e.setup(new Cyberpunk());
		e.runUntil(Condition.ALWAYS);
	}
	
	private boolean modeSet = false;
	public Screen currentScreen = new SplashScreen(this);
	
	private Hooks hooks = null;
	private int msSinceCursorMoved = 0;
	private Pt lastCursor = new Pt(0, 0);

	@Override
	public void input(Input in) {
		if (!modeSet) {
			int result = JOptionPane.showOptionDialog(null, "Choose a screen resolution.", "Cyberpunk", 0, 0, null, new String[] { "1200x720", "Fullscreen" }, "1200x720");
			if (result == 1) {
				ScreenMode best = null;
				for (ScreenMode m : in.modes()) {
					if (best == null || (m.width * m.height > best.width * best.height && m.fullscreen)) {
						best = m;
					}
				}
				if (best != null) {
					in.setMode(best);
				}
			} else {
				in.setMode(new ScreenMode(1200, 720, false));
			}
			modeSet = true;
		}
		currentScreen.input(in);
		if (hooks != null) {
			hooks.hit(in);
		}
		if (!lastCursor.equals(in.cursor())) {
			msSinceCursorMoved = 0;
			lastCursor = in.cursor();
			DrawUtils.tooltip = null;
		}
		msSinceCursorMoved += in.msDelta();
		DrawUtils.tooltipMs += in.msDelta();
	}

	@Override
	public void render(Frame f) {
		ScreenMode sm = f.mode();
		Draw d = new Draw(f);
		currentScreen.render(d, sm);
		hooks = d.getHooks();
		if (msSinceCursorMoved >= 300 && DrawUtils.tooltipMs >= 300 && DrawUtils.tooltip != null) {
			Rect r = d.textSize(DrawUtils.tooltip, OCRA, 0, 0, 300 - DrawUtils.PADDING * 2);
			int x = (int) f.cursor().x + 20;
			int y = (int) f.cursor().y;
			int w = (int) r.width + DrawUtils.PADDING * 2;
			int h = (int) r.height + DrawUtils.PADDING * 2;
			x = Math.min(x, sm.width - w);
			y = Math.min(y, sm.height - h);
			d.rect(DrawUtils.TOOLTIP, x, y, w, h);
			d.text(DrawUtils.TEXT_PREFIX + DrawUtils.tooltip, OCRA, x + DrawUtils.PADDING, y + DrawUtils.PADDING, 300 - DrawUtils.PADDING * 2);
		}
	}
}
