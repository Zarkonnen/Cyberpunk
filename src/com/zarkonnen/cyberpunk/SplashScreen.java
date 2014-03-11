package com.zarkonnen.cyberpunk;

import com.zarkonnen.catengine.Draw;
import com.zarkonnen.catengine.Input;
import com.zarkonnen.catengine.util.Pt;
import com.zarkonnen.catengine.util.ScreenMode;
import java.util.Random;

public class SplashScreen implements Screen {
	public static final String GAME_NAME = "GRIM MEATHOOK FUTURE SIMULATOR";
	
	public char[] name;
	public final Cyberpunk g;
	Random r = new Random();
	int heat = 100;
	int tick = 0;
	
	public static final String ALPHA = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM1234567890-=+_!?<>,.;:'@£$%^&*()|~/±";
		
	private char rndChar() {
		return ALPHA.charAt(1 + r.nextInt(ALPHA.length() - 1));
	}

	public SplashScreen(Cyberpunk g) {
		this.g = g;
		name = new char[GAME_NAME.length()];
		for (int i = 0; i < name.length; i++) {
			name[i] = rndChar();
		}
	}
	
	
	@Override
	public void input(Input in) {
		if (heat > 0) {
			for (int i = 0; i < name.length; i++) {
				if (i != GAME_NAME.charAt(i)) {
					if (r.nextInt(100) < heat) {
						if (r.nextInt(100) > heat) {
							name[i] = GAME_NAME.charAt(i);
						} else {
							name[i] = rndChar();
						}
					}
				}
			}
			heat--;
		}
		if (tick++ >= 200 || in.mouseDown() != null) {
			g.currentScreen = new SetupScreen(g);
		}
	}

	@Override
	public void render(Draw d, ScreenMode sm) {
		d.rect(DrawUtils.BG, 0, 0, sm.width, sm.height);
		String text = heat > 0 ? new String(name) : GAME_NAME;
		Pt pt = d.textSize(text, Cyberpunk.OCRA);
		d.text(DrawUtils.TEXT_PREFIX + text, Cyberpunk.OCRA, sm.width / 2 - (int) pt.x / 2, sm.height / 2 - (int) pt.y / 2);
	}
}
