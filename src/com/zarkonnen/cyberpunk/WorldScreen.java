
package com.zarkonnen.cyberpunk;

import com.zarkonnen.catengine.Draw;
import com.zarkonnen.catengine.Hook;
import com.zarkonnen.catengine.Hook.Type;
import com.zarkonnen.catengine.Input;
import com.zarkonnen.catengine.util.Pt;
import com.zarkonnen.catengine.util.ScreenMode;
import com.zarkonnen.catengine.util.Utils.Pair;
import java.util.List;
import static com.zarkonnen.catengine.util.Utils.*;
import com.zarkonnen.cyberpunk.interaction.TileInteraction;
import java.util.ArrayList;
import static com.zarkonnen.cyberpunk.DrawUtils.*;

public class WorldScreen implements Screen {
	public final GameState g;
	public final WorldMap m;
	private int topLeftX, topLeftY, topLeftZ;
	private int msSinceScroll;
	public final ButtonList inventory = new ButtonList();
	public final ButtonList interactions = new ButtonList();
	
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
		inventory.model = new InventoryModel();
		interactions.model = new InteractionModel();
	}
	
	private class InventoryModel implements ButtonList.Model {
		@Override
		public List<Button> buttons() {
			ArrayList<Button> l = new ArrayList<Button>();
			for (final Item it : g.player.inventory) {
				l.add(new Button() {
					@Override
					public String text() {
						return it.getName();
					}
					
					@Override
					public String tooltip() {
						return it.getName();
					}

					@Override
					public boolean enabled() {
						return false;
					}

					@Override
					public void run() {
						
					}
				});
			}
			
			return l;
		}
	}
	
	private class InteractionModel implements ButtonList.Model {
		@Override
		public List<Button> buttons() {
			ArrayList<Button> l = new ArrayList<Button>();
			for (final TileInteraction ti : g.player.location().getInteraction(g.player)) {
				l.add(new Button() {
					@Override
					public String text() {
						return ti.description();
					}
					
					@Override
					public String tooltip() {
						return null;
					}

					@Override
					public boolean enabled() {
						return ti.enabled();
					}

					@Override
					public void run() {
						g.player.message = ti.run();
					}
				});
			}
			
			return l;
		}
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
		
		inventory.input(in, 0, 0, 150, sm.height);
		interactions.input(in, sm.width - 150, 0, 150, sm.height);
		
		if (g.player.message != null && in.keyPressed("SPACE")) {
			g.player.message = null;
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
				d.text(t.type.name(), Cyberpunk.OCRA, screenX * GRID_SIZE, screenY * GRID_SIZE);
				if (g.player.location() == t) {
					d.text("player", Cyberpunk.OCRA, screenX * GRID_SIZE, screenY * GRID_SIZE + 20);
				}
			}
		}
		
		inventory.draw(d, 0, 0, 150, sm.height);
		interactions.draw(d, sm.width - 150, 0, 150, sm.height);
		
		if (g.player.message != null) {
			d.rect(BG, sm.width / 2 - 150, sm.height / 2 - 150, 300, 300, new Hook(Hook.Type.MOUSE_1_CLICKED) {
				@Override
				public void run(Input in, Pt p, Type type) {
					g.player.message = null;
				}
			});
			d.text(TEXT_PREFIX + g.player.message, Cyberpunk.OCRA, sm.width / 2 - 150 + PADDING, sm.height / 2 - 150 + PADDING, 300 - PADDING * 2);
		}
	}
}
