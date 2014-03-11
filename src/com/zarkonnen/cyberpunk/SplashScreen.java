package com.zarkonnen.cyberpunk;

import com.zarkonnen.catengine.Draw;
import com.zarkonnen.catengine.Input;
import com.zarkonnen.catengine.util.Pt;
import com.zarkonnen.catengine.util.ScreenMode;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Random;

public class SplashScreen implements Screen {
	public static final String GAME_NAME = "GRIM MEATHOOK FUTURE SIMULATOR";
	public static final String GAME_CREDITS = "A Cyberpunkjam 2014 game by Rachel Knowler and David Stark.";
	
	public char[] name;
	public final Cyberpunk g;
	Random r = new Random();
	int heat = 100;
	int tick = 0;
	
	public static final String ALPHA = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM1234567890-=+_!?<>,.;:'@£$%^&*()|~/±";
		
	private char rndChar() {
		return ALPHA.charAt(1 + r.nextInt(ALPHA.length() - 1));
	}
	
	private GameState load() {
		File f = new File("cyberpunk_save");
		if (!f.exists()) { return null; }
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(new FileInputStream(f));
			return (GameState) ois.readObject();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			try { ois.close(); } catch (Exception e) {}
		}
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
		if (tick++ >= 250 || in.mouseDown() != null) {
			GameState gs = load();
			if (gs == null) {
				g.currentScreen = new SetupScreen(g);
			} else {
				g.currentScreen = new WorldScreen(g, gs);
			}
		}
	}

	@Override
	public void render(Draw d, ScreenMode sm) {
		d.rect(DrawUtils.BG, 0, 0, sm.width, sm.height);
		String text = heat > 0 ? new String(name) : GAME_NAME;
		Pt pt = d.textSize(text, Cyberpunk.OCRA);
		d.text(DrawUtils.TEXT_PREFIX + text, Cyberpunk.OCRA, sm.width / 2 - (int) pt.x / 2, sm.height / 2 - (int) pt.y / 2);
		if (tick > 150) {
			pt = d.textSize(GAME_CREDITS, Cyberpunk.OCRA);
			d.text(DrawUtils.TEXT_PREFIX + GAME_CREDITS, Cyberpunk.OCRA, sm.width / 2 - (int) pt.x / 2, sm.height / 2 - (int) pt.y / 2 + 30);
		}
	}
}
