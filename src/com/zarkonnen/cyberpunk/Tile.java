package com.zarkonnen.cyberpunk;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Tile implements Serializable {
	public final WorldMap map;
	public final TileType type;
	public final int x, y, z;

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
