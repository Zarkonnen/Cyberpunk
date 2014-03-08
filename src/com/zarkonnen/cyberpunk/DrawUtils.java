package com.zarkonnen.cyberpunk;

import com.zarkonnen.catengine.Draw;
import com.zarkonnen.catengine.Hook;
import com.zarkonnen.catengine.Hook.Type;
import com.zarkonnen.catengine.Input;
import com.zarkonnen.catengine.util.Clr;
import com.zarkonnen.catengine.util.Pt;
import com.zarkonnen.catengine.util.Rect;

public final class DrawUtils {
	private DrawUtils() {}
	
	public static final int BUTTON_H = 30;
	public static final int PADDING = 7;
	public static final int MARGIN_X = 7;
	public static final int MARGIN_Y = 2;
	public static final String BG_HEX = "264a4f";
	public static final Clr BG = Clr.fromHex(BG_HEX);
	public static final String TEXT_HEX = "dfecdd";
	public static final String TEXT_PREFIX = "[default=" + TEXT_HEX + "][" + TEXT_HEX + "]";
	public static final Clr TEXT = Clr.fromHex(TEXT_HEX);
	public static final String BUTTON_HEX = "aa00ea";
	public static final Clr BUTTON = Clr.fromHex(BUTTON_HEX);
	public static final String DISABLED_BUTTON_HEX = "856890";
	public static final Clr DISABLED_BUTTON = Clr.fromHex(DISABLED_BUTTON_HEX);
	public static final String HOVER_BUTTON_HEX = "b900ff";
	public static final Clr HOVER_BUTTON = Clr.fromHex(HOVER_BUTTON_HEX);
	
	
	public static void button(Draw d, int x, int y, int w, Button b) {
		button(d, b.text(), x, y, w, b, b.enabled(), false);
	}
	
	public static void button(Draw d, String text, int x, int y, int w, final Runnable onClick, boolean enabled, boolean repeat) {
		Clr c = Rect.contains(x, y, w, BUTTON_H, d.cursor()) ? HOVER_BUTTON : BUTTON;
		if (!enabled) {
			c = DISABLED_BUTTON;
		}
		d.rect(c, x, y, w, BUTTON_H);
		d.text(TEXT_PREFIX + text, Cyberpunk.OCRA, x + 7, y + 5);
		if (enabled) {
			d.hook(x, y, w, BUTTON_H, new Hook(repeat ? Hook.Type.MOUSE_1_DOWN : Hook.Type.MOUSE_1_CLICKED) {
				@Override
				public void run(Input in, Pt p, Type type) {
					onClick.run();
				}
			});
		}
	}
}
