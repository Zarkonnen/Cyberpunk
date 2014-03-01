
package com.zarkonnen.cyberpunk;

import com.zarkonnen.catengine.Draw;
import com.zarkonnen.catengine.Input;
import com.zarkonnen.catengine.util.ScreenMode;

public class WorldScreen implements Screen {
	public final WorldMap m;
	private int topLeftX, topLeftY, topLeftZ;
	private int msSinceScroll;
	
	public static final int GRID_SIZE = 100;
	public static final int MS_PER_SCROLL = 200;

	public WorldScreen(WorldMap m) {
		this.m = m;
	}

	@Override
	public void input(Input in) {
		msSinceScroll += in.msDelta();
		if (msSinceScroll >= MS_PER_SCROLL) {
			if (in.keyDown("LEFT")) {
				topLeftX--;
				msSinceScroll = 0;
			}
			if (in.keyDown("RIGHT")) {
				topLeftX++;
				msSinceScroll = 0;
			}
			if (in.keyDown("UP")) {
				topLeftY--;
				msSinceScroll = 0;
			}
			if (in.keyDown("DOWN")) {
				topLeftY++;
				msSinceScroll = 0;
			}
			if (in.keyDown("COMMA")) {
				topLeftZ--;
				msSinceScroll = 0;
			}
			if (in.keyDown("PERIOD")) {
				topLeftZ++;
				msSinceScroll = 0;
			}
		}
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
			}
		}
	}
}
