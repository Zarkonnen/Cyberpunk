package com.zarkonnen.cyberpunk;

import com.zarkonnen.cyberpunk.interaction.Adultery;
import com.zarkonnen.cyberpunk.interaction.Attack;
import com.zarkonnen.cyberpunk.interaction.BreakIn;
import com.zarkonnen.cyberpunk.interaction.Datajack;
import com.zarkonnen.cyberpunk.interaction.Datatrawl;
import com.zarkonnen.cyberpunk.interaction.DoWork;
import com.zarkonnen.cyberpunk.interaction.Factories;
import com.zarkonnen.cyberpunk.interaction.Gamble;
import com.zarkonnen.cyberpunk.interaction.HackIn;
import com.zarkonnen.cyberpunk.interaction.HarvestImplants;
import com.zarkonnen.cyberpunk.interaction.Interaction;
import com.zarkonnen.cyberpunk.interaction.ItemInteraction;
import com.zarkonnen.cyberpunk.interaction.Lock;
import com.zarkonnen.cyberpunk.interaction.Loot;
import com.zarkonnen.cyberpunk.interaction.Mug;
import com.zarkonnen.cyberpunk.interaction.Murder;
import com.zarkonnen.cyberpunk.interaction.Scavenge;
import com.zarkonnen.cyberpunk.interaction.StealFromEmployers;
import com.zarkonnen.cyberpunk.interaction.Stun;
import com.zarkonnen.cyberpunk.interaction.Unlock;
import com.zarkonnen.cyberpunk.interaction.VisitBrothel;
import com.zarkonnen.cyberpunk.interaction.VisitDrugDen;
import com.zarkonnen.cyberpunk.interaction.WatchHorriblePorn;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Tile implements Serializable, HasName {
	public final WorldMap map;
	public final TileType type;
	public final int x, y, z;
	public final ArrayList<HiddenItem> hiddenItems = new ArrayList<HiddenItem>();
	public final ArrayList<Gadget> gadgets = new ArrayList<Gadget>();
	public boolean locked = false;
	public int breakInResistance = 0;
	public int hackInResistance = 0;
	public transient int pathDist;
	public transient Tile bestPath;
	
	// Pathing
	HashMap<Tile, Tile> towardsTile = new HashMap<Tile, Tile>();
	EnumMap<TileType, Tile> towardsTileType = new EnumMap<TileType, Tile>(TileType.class);
	Tile towardsEdge;
	
	public Tile towardsTile(Tile t) {
		if (!towardsTile.containsKey(t)) {
			map.calcPathsFor(t);
		}
		return towardsTile.get(t);
	}
	
	public Tile towardsTileType(TileType tt) {
		if (!towardsTileType.containsKey(tt)) {
			map.calcPathsFor(tt);
		}
		return towardsTileType.get(tt);
	}
	
	public Tile towardsEdge() {
		return towardsEdge;
	}

	public boolean atMapEdge() {
		return map.atMapEdge(x, y);
	}

	public boolean lockedFor(Person actor) {
		if (!locked) { return false; }
		for (Item item : actor.inventory) {
			if (item.keyFor == this) {
				return false;
			}
		}
		return true;
	}
	
	public boolean inBusiness() {
		for (Person p : people()) {
			if (p.workplace == this) { return true; }
		}
		return false;
	}
	
	public static class HiddenItem implements Serializable {
		public final int hidingScore;
		public final Item item;

		public HiddenItem(int hidingScore, Item item) {
			this.hidingScore = hidingScore;
			this.item = item;
		}
	}
	
	public static class Gadget implements Serializable {
		public final int hidingScore;
		public final Person owner;
		public final Item item;
		
		public Gadget(int hidingScore, Person owner, Item item) {
			this.hidingScore = hidingScore;
			this.owner = owner;
			this.item = item;
		}
	}
	
	public List<Interaction<?>> getInteractions(Person p) {
		ArrayList<Interaction<?>> l = new ArrayList<Interaction<?>>();
		l.addAll(Factories.make(p, this));
		for (Person p2 : people()) {
			if (p != p2) {
				l.addAll(Factories.make(p, p2));
			}
		}
		return l;
	}

	public Tile(WorldMap map, TileType type, int x, int y, int z) {
		this.map = map;
		this.type = type;
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public static final HashSet<Class> BLACKMAILABLES = new HashSet<Class>();
	static {
		Class[] cls = {
			Attack.class,
			BreakIn.class,
			Datajack.class,
			Gamble.class,
			HackIn.class,
			HarvestImplants.class,
			Loot.class,
			Mug.class,
			Murder.class,
			Scavenge.class,
			Stun.class,
			VisitBrothel.class,
			VisitDrugDen.class,
			Adultery.class,
			WatchHorriblePorn.class,
			StealFromEmployers.class
		};
		BLACKMAILABLES.addAll(Arrays.asList(cls));
	};
	
	public static final HashSet<Class> WIRETAP_BLACKMAILABLES = new HashSet<Class>();
	static {
		Class[] cls = {
			Datatrawl.class,
			HackIn.class,
		};
		WIRETAP_BLACKMAILABLES.addAll(Arrays.asList(cls));
	};
	
	public void observe(Interaction i) {
		if (BLACKMAILABLES.contains(i.getClass())) {
			Item material = new Item(ItemType.BLACKMAIL_MATERIAL);
			material.blackmailFor = i.actor();
			material.recordOf = i;
			
			for (Gadget g : gadgets) {
				if (g.item.type == ItemType.BUG && map.r.nextInt(100) < g.item.type.bug) {
					g.item.found.add(material);
				}
			}
		}
		
		if (WIRETAP_BLACKMAILABLES.contains(i.getClass())) {
			Item material = new Item(ItemType.BLACKMAIL_MATERIAL);
			material.blackmailFor = i.actor();
			material.recordOf = i;
			
			for (Gadget g : gadgets) {
				if ((g.item.type == ItemType.WIRETAP || g.item.type == ItemType.REMOTE_WIRETAP) && map.r.nextInt(100) < g.item.type.bug) {
					g.item.found.add(material);
				}
			}
		}
		
		if (i instanceof DoWork) {
			for (Item it : i.actor().inventory) {
				if (it.type.data) {
					for (Gadget g : gadgets) {
						if (!g.item.found.contains(it) && (g.item.type == ItemType.WIRETAP || g.item.type == ItemType.REMOTE_WIRETAP) && map.r.nextInt(100) < g.item.type.bug) {
							g.item.found.add(it);
						}
					}
				}
			}
			for (HiddenItem hit : i.actor().location().hiddenItems) {
				Item it = hit.item;
				if (it.type.data) {
					for (Gadget g : gadgets) {
						if (!g.item.found.contains(it) && (g.item.type == ItemType.WIRETAP || g.item.type == ItemType.REMOTE_WIRETAP) && g.item.type.bug > hit.hidingScore) {
							g.item.found.add(it);
						}
					}
				}
			}
		}
		
		if (i instanceof Lock || i instanceof Unlock) {
			Item key = ((ItemInteraction) i).getItem();
			if (key.type.data) {
				for (Gadget g : gadgets) {
					if (!g.item.found.contains(key) && (g.item.type == ItemType.WIRETAP || g.item.type == ItemType.REMOTE_WIRETAP) && map.r.nextInt(100) < g.item.type.bug) {
						g.item.found.add(key);
					}
				}
			}
		}
	}
	
	@Override
	public String getName() {
		return type.name().toLowerCase();
	}
	
	public List<Person> people() {
		ArrayList<Person> l = new ArrayList<Person>();
		for (Person p : map.people()) {
			if (p.location() == this) {
				l.add(p);
			}
		}
		return l;
	}
	
	public Tile in(Direction d) {
		return map.at(x + d.dx, y + d.dy, z + d.dz);
	}
	
	public boolean passable(Direction d) {
		return type.passable.contains(d) && in(d).type.passable.contains(Direction.reverse(d));
	}
}
