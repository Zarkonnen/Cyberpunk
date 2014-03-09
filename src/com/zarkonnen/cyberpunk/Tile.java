package com.zarkonnen.cyberpunk;

import com.zarkonnen.cyberpunk.interaction.Factories;
import com.zarkonnen.cyberpunk.interaction.Interaction;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Tile implements Serializable {
	public final WorldMap map;
	public final TileType type;
	public final int x, y, z;
	public final ArrayList<Item> inventory = new ArrayList<Item>();
	public final ArrayList<HiddenItem> hiddenItems = new ArrayList<HiddenItem>();
	
	public static class HiddenItem implements Serializable {
		public final int hidingScore;
		public final Item item;

		public HiddenItem(int hidingScore, Item item) {
			this.hidingScore = hidingScore;
			this.item = item;
		}
	}
	
	public List<Interaction<Tile>> getInteractions(Person p) {
		return Factories.make(p, this);
	}

	public Tile(WorldMap map, TileType type, int x, int y, int z) {
		this.map = map;
		this.type = type;
		this.x = x;
		this.y = y;
		this.z = z;
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
