package com.zarkonnen.cyberpunk;

import com.zarkonnen.catengine.Draw;
import com.zarkonnen.catengine.Input;
import com.zarkonnen.catengine.util.ScreenMode;
import static com.zarkonnen.cyberpunk.DrawUtils.*;

public class PreSimScreen implements Screen {
	public final Cyberpunk g;
	public final GameState gs;
	public final CharacterClass cc;
	public int tick;
	
	public static final int PRESIM_TICKS = (24 * 6 + 10 * 6);

	public PreSimScreen(Cyberpunk g, GameState gs, CharacterClass cc) {
		this.g = g;
		this.gs = gs;
		this.cc = cc;
	}

	@Override
	public void input(Input in) {
		gs.map.tick();
		tick++;
		if (tick > PRESIM_TICKS) {
			gs.player = gs.map.makePlayer(cc);
			g.currentScreen = new WorldScreen(g, gs);
		}
	}

	@Override
	public void render(Draw d, ScreenMode sm) {
		d.rect(BG, 0, 0, sm.width, sm.height);
		d.text(TEXT_PREFIX + "Simulating the world for a bit...\n" + tick + "/" + PRESIM_TICKS, Cyberpunk.OCRA, sm.width / 2 - 125, sm.height / 2);
		StringBuilder iqd = new StringBuilder();
		for (ItemType t : ItemType.values()) {
			int n = gs.map.itemQuantity(t);
			if (n > 0) {
				iqd.append(t.name).append(": ").append(n).append("\n");
			}
		}
		d.text(iqd.toString(), Cyberpunk.OCRA, 10, 10);
	}
}
