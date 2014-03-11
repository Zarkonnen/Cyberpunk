package com.zarkonnen.cyberpunk;

import com.zarkonnen.catengine.Draw;
import com.zarkonnen.catengine.Input;
import com.zarkonnen.catengine.util.Clr;
import com.zarkonnen.catengine.util.Pt;
import com.zarkonnen.catengine.util.ScreenMode;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Random;

public class SplashScreen implements Screen {
	public static final Clr BG_BRIGHTER = DrawUtils.BG.mix(0.15, Clr.WHITE);
	public static final Clr BG_BRIGHTERER = DrawUtils.BG.mix(0.25, Clr.WHITE);
	
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
		if (tick++ >= 280 || in.mouseDown() != null) {
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
		if (tick > 150) {
			int x = sm.width / 2 - 100;
			int y = sm.height / 2 - 100;
			int b = y + 190;
			
			int amt = Math.min(255, (tick - 180) * 5);
			Clr brighter = new Clr(BG_BRIGHTER.r, BG_BRIGHTER.g, BG_BRIGHTER.b, amt);
			amt = Math.min(255, (tick - 150) * 5);
			Clr brighterer = new Clr(BG_BRIGHTERER.r, BG_BRIGHTERER.g, BG_BRIGHTERER.b, amt);
			d.rect(brighter, x + 51, b - 158, 33, 158);
			d.rect(brighter, x + 107, b - 91, 27, 3);
			d.rect(brighter, x + 11, b - 31, 19, 3);
			d.rect(brighter, x + 157, b - 47, 21, 3);
			d.rect(brighterer, x, b - 2, 200, 2);
			d.rect(brighterer, x + 3, b - 12, 20, 12);
			d.rect(brighterer, x + 31, b - 8, 24, 8);
			d.rect(brighterer, x + 62, b - 10, 3, 10);
			d.rect(brighterer, x + 71, b - 9, 3, 9);
			d.rect(brighterer, x + 81, b - 7, 33, 7);
			d.rect(brighterer, x + 121, b - 18, 16, 18);
			d.rect(brighterer, x + 141, b - 17, 15, 17);
			d.rect(brighterer, x + 151, b - 5, 33, 5);
			d.rect(brighterer, x + 194, b - 7, 3, 7);
			
			Pt pt2 = d.textSize(GAME_CREDITS, Cyberpunk.OCRA);
			d.text(DrawUtils.TEXT_PREFIX + GAME_CREDITS, Cyberpunk.OCRA, sm.width / 2 - (int) pt2.x / 2, sm.height / 2 - (int) pt2.y / 2 + 30);
		}
		
		d.text(DrawUtils.TEXT_PREFIX + text, Cyberpunk.OCRA, sm.width / 2 - (int) pt.x / 2, sm.height / 2 - (int) pt.y / 2);
	}
}
