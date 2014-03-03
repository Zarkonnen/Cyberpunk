
package com.zarkonnen.cyberpunk;

import com.zarkonnen.catengine.Draw;
import com.zarkonnen.catengine.Input;
import com.zarkonnen.catengine.util.ScreenMode;
import com.zarkonnen.catengine.util.Utils.Pair;
import java.util.List;
import static com.zarkonnen.catengine.util.Utils.*;

public class WorldScreen implements Screen {
	public final GameState g;
	public final WorldMap m;
	private int topLeftX, topLeftY, topLeftZ;
	private int msSinceScroll;
	
	public static final int GRID_SIZE = 100;
	public static final int MS_PER_SCROLL = 200;
	public static final List<Pair<String, Direction>> DIRECTION_KEYS = l(
		p("LEFT", Direction.WEST),
		p("RIGHT", Direction.EAST),
		p("UP", Direction.NORTH),
		p("DOWN", Direction.SOUTH),
		p("COMMA", Direction.UP),
		p("PERIOD", Direction.DOWN)
	);

	public WorldScreen(GameState g) {
		this.g = g;
		m = g.map;
	}

	@Override
	public void input(Input in) {
		msSinceScroll += in.msDelta();
		if (msSinceScroll >= MS_PER_SCROLL) {
			for (Pair<String, Direction> dk : DIRECTION_KEYS) {
				if (in.keyDown(dk.a)) {
					g.player.moveBy(dk.b);
					msSinceScroll = 0;
				}
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
