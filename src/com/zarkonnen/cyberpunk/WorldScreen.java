
package com.zarkonnen.cyberpunk;

import com.zarkonnen.catengine.Draw;
import com.zarkonnen.catengine.Input;
import com.zarkonnen.catengine.util.ScreenMode;

public class WorldScreen implements Screen {
	public final GameState g;
	public final WorldMap m;
	private int topLeftX, topLeftY, topLeftZ;
	private int msSinceScroll;
	
	public static final int GRID_SIZE = 100;
	public static final int MS_PER_SCROLL = 200;

	public WorldScreen(GameState g) {
		this.g = g;
		m = g.map;
	}

	@Override
	public void input(Input in) {
		msSinceScroll += in.msDelta();
		if (msSinceScroll >= MS_PER_SCROLL) {
			if (in.keyDown("LEFT")) {
				g.player.moveBy(-1, 0, 0);
				msSinceScroll = 0;
			}
			if (in.keyDown("RIGHT")) {
				g.player.moveBy(1, 0, 0);
				msSinceScroll = 0;
			}
			if (in.keyDown("UP")) {
				g.player.moveBy(0, -1, 0);
				msSinceScroll = 0;
			}
			if (in.keyDown("DOWN")) {
				g.player.moveBy(0, 1, 0);
				msSinceScroll = 0;
			}
			if (in.keyDown("COMMA")) {
				g.player.moveBy(0, 0, -1);
				msSinceScroll = 0;
			}
			if (in.keyDown("PERIOD")) {
				g.player.moveBy(0, 0, 1);
				msSinceScroll = 0;
			}
		}
		
		ScreenMode sm = in.mode();
		int screenGridW = sm.width / GRID_SIZE;
		int screenGridH = sm.height / GRID_SIZE;
		
		topLeftX = g.player.location().x - screenGridW / 2;
		topLeftY = g.player.location().y - screenGridH / 2;
		topLeftZ = g.player.location().z;
	}

	@Override
	public void render(Draw d, ScreenMode sm) {
		for (int screenY = 0; screenY < sm.height / GRID_SIZE + 1; screenY++) {
			for (int screenX = 0; screenX < sm.width / GRID_SIZE + 1; screenX++) {
				int mapZ = topLeftZ;
				int mapY = screenY + topLeftY;
				int mapX = screenX + topLeftX;
				Tile t = m.at(mapX, mapY, mapZ);
				d.rect(t.type.color, screenX * GRID_SIZE, screenY * GRID_SIZE, GRID_SIZE, GRID_SIZE);
				if (g.player.location() == t) {
					d.text("player", Cyberpunk.OCRA, screenX * GRID_SIZE, screenY * GRID_SIZE);
				}
			}
		}
	}
}
