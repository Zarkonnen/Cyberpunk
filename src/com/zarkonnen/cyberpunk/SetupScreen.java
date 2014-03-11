package com.zarkonnen.cyberpunk;

import com.zarkonnen.catengine.Draw;
import com.zarkonnen.catengine.Input;
import com.zarkonnen.catengine.util.ScreenMode;
import static com.zarkonnen.cyberpunk.DrawUtils.*;

public class SetupScreen implements Screen {
	public final Cyberpunk g;

	public SetupScreen(Cyberpunk g) {
		this.g = g;
	}
	

	@Override
	public void input(Input in) {
	}
	

	@Override
	public void render(Draw d, ScreenMode sm) {
		d.rect(BG, 0, 0, sm.width, sm.height);
		
		int h = (BUTTON_H + MARGIN_Y) * CharacterClass.values().length + 50;
		int w = 250;
		
		int x = sm.width / 2 - w / 2;
		int y = sm.height / 2 - h / 2;
		
		d.text(TEXT_PREFIX + "GRIM MEATHOOK FUTURE SIMULATOR\nChoose your character class:", Cyberpunk.OCRA, x, y);
		y += 50 + MARGIN_Y;
		for (final CharacterClass cc : CharacterClass.values()) {
			button(d, cc.name, cc.desc, x, y, w, new Runnable() {
				@Override
				public void run() {
					GameState gs = new GameState(System.currentTimeMillis(), 24, 24, 12);
					g.currentScreen = new PreSimScreen(g, gs, cc);
				}
			}, true, false);
			y += BUTTON_H + MARGIN_Y;
		}
	}
}
