
package com.zarkonnen.cyberpunk;

import com.zarkonnen.catengine.Draw;
import com.zarkonnen.catengine.Hook;
import com.zarkonnen.catengine.Hook.Type;
import com.zarkonnen.catengine.Input;
import com.zarkonnen.catengine.util.Clr;
import com.zarkonnen.catengine.util.Pt;
import com.zarkonnen.catengine.util.ScreenMode;
import com.zarkonnen.catengine.util.Utils.Pair;
import java.util.List;
import static com.zarkonnen.catengine.util.Utils.*;
import com.zarkonnen.cyberpunk.interaction.Interaction;
import java.util.ArrayList;
import static com.zarkonnen.cyberpunk.DrawUtils.*;
import com.zarkonnen.cyberpunk.interaction.Buy;
import com.zarkonnen.cyberpunk.interaction.Factories;
import com.zarkonnen.cyberpunk.interaction.ItemInteraction;
import com.zarkonnen.cyberpunk.interaction.MoveInDirection;
import com.zarkonnen.cyberpunk.interaction.SellToBusiness;
import com.zarkonnen.cyberpunk.interaction.SellToPerson;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;

public class WorldScreen implements Screen {
	public final GameState g;
	public final WorldMap m;
	private int topLeftX, topLeftY, topLeftZ;
	private int msSinceScroll;
	public final ButtonList inventory = new ButtonList();
	public final ButtonList interactions = new ButtonList();
	public static final int LIST_W = 250;
	
	public static final int GRID_SIZE = 200;
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
			l.add(new Button() {
				@Override
				public String text() {
					return g.map.timeOfDay() / 6 + ":" + (g.map.timeOfDay() % 6) * 10;
				}

				@Override
				public String tooltip() { return null; }

				@Override
				public boolean enabled() { return false; }

				@Override
				public void run() {}
			});
			l.add(new Button() {
				@Override
				public String text() {
					String clr;
					if (g.player.health <= Person.H_VERY_BADLY_INJURED) {
						clr = "[RED]Very Badly Injured";
					} else if (g.player.health <= Person.H_BADLY_INJURED) {
						clr = "[ORANGE]Badly Injured";
					} else if (g.player.health <= Person.H_INJURED) {
						clr = "[YELLOW]Injured";
					} else if (g.player.health <= Person.H_BRUISED) {
						clr = "Bruised";
					} else {
						clr = "Fine";
					}
					return "Health: " + clr;
				}

				@Override
				public String tooltip() { return null; }

				@Override
				public boolean enabled() { return false; }

				@Override
				public void run() {}
			});
			l.add(new Button() {
				@Override
				public String text() {
					String rep;
					if (g.player.reputation < 25) {
						rep = "[RED]Very Bad";
					} else if (g.player.reputation < 50) {
						rep = "[cc8822]Bad";
					} else if (g.player.reputation < 80) {
						rep = "[YELLOW]Impaired";
					} else if (g.player.reputation < 95) {
						rep = "Fine";
					} else {
						rep = "Spotless";
					}
					return "Reputation: " + rep;
				}

				@Override
				public String tooltip() { return null; }

				@Override
				public boolean enabled() { return false; }

				@Override
				public void run() {}
			});
			l.add(new Button() {
				@Override
				public String text() {
					String hng;
					if (g.player.hunger >= Person.H_VERY_HUNGRY) {
						hng = "[cc8822]Very";
					} else if (g.player.hunger >= Person.H_HUNGRY) {
						hng = "[YELLOW]Slight";
					} else {
						hng = "None";
					}
					return "Hunger: " + hng;
				}

				@Override
				public String tooltip() { return null; }

				@Override
				public boolean enabled() { return false; }

				@Override
				public void run() {}
			});
			l.add(new Button() {
				@Override
				public String text() {
					int visex = g.player.getVisibleExhaustion();
					String ex;
					if (visex > Person.EX_EXHAUSTED) {
						ex = "[RED]Exhausted";
					} else if (visex >= Person.EX_VERY_TIRED) {
						ex = "[cc8822]Very Tired";
					} else if (visex >= Person.EX_TIRED) {
						ex = "[YELLOW]Tired";
					} else if (visex >= Person.EX_WIRED) {
						ex = "Fine";
					} else {
						ex = "[55ffff]Wired";
					}
					return "Exhaustion: " + ex;
				}

				@Override
				public String tooltip() { return null; }

				@Override
				public boolean enabled() { return false; }

				@Override
				public void run() {}
			});
			for (final Skill sk : Skill.values()) {
				final int amt = g.player.getSkill(sk);
				if (amt > 0) {
					l.add(new Button() {
						@Override
						public String text() {
							return sk.name() + ": " + amt;
						}

						@Override
						public String tooltip() { return null; }

						@Override
						public boolean enabled() { return false; }

						@Override
						public void run() {}
					});
				}
			}
			l.add(new Button() {
				@Override
				public String text() {
					return "$" + g.player.money;
				}

				@Override
				public String tooltip() { return null; }

				@Override
				public boolean enabled() { return false; }

				@Override
				public void run() {}
			});
			EnumMap<ItemType, Integer> counts = new EnumMap<ItemType, Integer>(ItemType.class);
			for (ItemType it : ItemType.values()) {
				counts.put(it, 0);
			}
			for (final Item it : g.player.allItems()) {
				counts.put(it.type, counts.get(it.type) + 1);
			}
			for (final Item it : g.player.allItems()) {
				if (!counts.containsKey(it.type)) {
					continue;
				}
				final int count = counts.get(it.type);
				counts.remove(it.type);
				List<Interaction<Item>> is = Factories.make(g.player, it);
				final Interaction<Item> interaction = is.isEmpty() ? null : is.get(0);
				l.add(new Button() {
					@Override
					public String text() {
						return count > 1 ? count + "x " + it.getName() : it.getName();
					}

					@Override
					public String tooltip() {
						if (interaction == null) {
							return it.getName();
						} else {
							return interaction.description();
						}
					}

					@Override
					public boolean enabled() {
						return interaction != null && interaction.enabled();
					}

					@Override
					public void run() {
						if (interaction != null) {
							g.playerAction(interaction);
						}
					}
				});
			}
			
			return l;
		}
	}
	
	private class InteractionModel implements ButtonList.Model {
		@Override
		public List<Button> buttons() {
			EnumSet<ItemType> hasBuy = EnumSet.noneOf(ItemType.class);
			EnumSet<ItemType> hasSell = EnumSet.noneOf(ItemType.class);
			HashSet<Tile> hasBuyKey = new HashSet<Tile>();
			HashSet<Tile> hasSellKey = new HashSet<Tile>();
			ArrayList<Button> l = new ArrayList<Button>();
			for (final Interaction ti : g.player.location().getInteractions(g.player)) {
				if (ti instanceof Buy) {
					Buy b = (Buy) ti;
					if (b.item.keyFor != null) {
						if (hasBuyKey.contains(b.item.keyFor)) {
							continue;
						} else {
							hasBuyKey.add(b.item.keyFor);
						}
					} else {
						if (hasBuy.contains(b.item.type)) {
							continue;
						} else {
							hasBuy.add(b.item.type);
						}
					}
				}
				if (ti instanceof SellToBusiness || ti instanceof SellToPerson) {
					ItemInteraction ii = (ItemInteraction) ti;
					Item item = ii.getItem();
					if (item.keyFor != null) {
						if (hasSellKey.contains(item.keyFor)) {
							continue;
						} else {
							hasSellKey.add(item.keyFor);
						}
					} else {
						if (hasSell.contains(item.type)) {
							continue;
						} else {
							hasSell.add(item.type);
						}
					}
				}
				l.add(new Button() {
					@Override
					public String text() {
						return ti.name();
					}
					
					@Override
					public String tooltip() {
						return ti.description();
					}

					@Override
					public boolean enabled() {
						return ti.enabled();
					}

					@Override
					public void run() {
						g.playerAction(ti);
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
					g.playerAction(new MoveInDirection(dk.b, g.player, g.player.location()));
					interactions.scroll = 0;
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
		
		inventory.input(in, 0, 0, LIST_W, sm.height);
		interactions.input(in, sm.width - LIST_W, 0, LIST_W, sm.height);
		
		if (!g.player.messages.isEmpty() && in.keyPressed("SPACE")) {
			g.player.messages.pollFirst();
		}
	}

	@Override
	public void render(Draw d, ScreenMode sm) {
		if (g.player.unconscious()) {
			d.rect(Clr.BLACK, 0, 0, sm.width, sm.height);
			d.text(TEXT_PREFIX + "You are unconscious.", Cyberpunk.OCRA, sm.width / 2 - 125, sm.height / 2);
			button(d, "Wait to wake up", null, sm.width / 2 - 125, sm.height / 2 + 30, 250, new Runnable() {
				@Override
				public void run() {
					g.map.tick();
				}
			}, true, true);
		} else {
			for (int screenY = 0; screenY < sm.height / GRID_SIZE + 1; screenY++) {
				for (int screenX = 0; screenX < sm.width / GRID_SIZE + 1; screenX++) {
					int mapZ = topLeftZ;
					int mapY = screenY + topLeftY;
					int mapX = screenX + topLeftX;
					Tile t = m.at(mapX, mapY, mapZ);
					int x = screenX * GRID_SIZE;// + GRID_SIZE / 2;
					int y = screenY * GRID_SIZE;// + GRID_SIZE / 2;;
					if (g.player.location() == t) {
						d.rect(Clr.WHITE, x, y, GRID_SIZE, GRID_SIZE);
						d.rect(t.type.color, x + 1, y + 1, GRID_SIZE - 2, GRID_SIZE - 2);
					} else {
						d.rect(t.type.color, x, y, GRID_SIZE, GRID_SIZE);
					}
					x += 2;
					y += 2;
					d.text(t.getName(), Cyberpunk.OCRA, x, y);
					y += 20;
					if (g.player.location() == t) {
						d.text("player", Cyberpunk.OCRA, x, y);
						y += 20;
					}
					if (g.player.home == t) {
						d.text("HOME", Cyberpunk.OCRA, x, y);
						y += 20;
					}
					if (g.player.hasKeyFor(t)) {
						d.text("KEY", Cyberpunk.OCRA, x, y);
						y += 20;
					}
					for (Tile.Gadget gad : t.gadgets) {
						if (gad.owner == g.player) {
							d.text(gad.item.getName(), Cyberpunk.OCRA, x, y);
							y += 20;
						}
					}
					for (Person p : t.people()) {
						if (p != g.player) {
							d.text(p.getName(), Cyberpunk.OCRA, x, y);
							y += 20;
						}
					}
				}
			}

			inventory.draw(d, 0, 0, LIST_W, sm.height);
			interactions.draw(d, sm.width - LIST_W, 0, LIST_W, sm.height);
		}
		
		if (!g.player.messages.isEmpty()) {
			d.rect(BG, sm.width / 2 - 150, sm.height / 2 - 150, 300, 300, new Hook(Hook.Type.MOUSE_1_CLICKED) {
				@Override
				public void run(Input in, Pt p, Type type) {
					g.player.messages.pollFirst();
				}
			});
			d.text(TEXT_PREFIX + g.player.messages.get(0), Cyberpunk.OCRA, sm.width / 2 - 150 + PADDING, sm.height / 2 - 150 + PADDING, 300 - PADDING * 2);
		}
	}
}
