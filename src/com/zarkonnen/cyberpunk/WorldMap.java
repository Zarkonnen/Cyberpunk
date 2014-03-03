package com.zarkonnen.cyberpunk;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public final class WorldMap implements Serializable {
	private final Tile[][][] map;
	private final LinkedList<Person> people = new LinkedList<Person>();
	
	public List<Person> people() {
		return people;
	}
	
	public Tile at(int x, int y, int z) {
		if (z < 0 || z >= map.length || y < 0 || y >= map[0].length || x < 0 || x >= map[0][0].length) {
			return new Tile(this, TileType.NOTHING, x, y, z);
		}
		return map[z][y][x];
	}
	
	public boolean typeAt(TileType t, int x, int y, int z) {
		return at(x, y, z).type == t;
	}
	
	public WorldMap(long seed, int xS, int yS, int zS) {
		Random r = new Random(seed);
		map = new Tile[zS][yS][xS];
		for (int z = 0; z < zS; z++) { for (int y = 0; y < yS; y++) { for (int x = 0; x < xS; x++) {
			map[z][y][x] = new Tile(this, TileType.AIR, x, y, z);
		}}}
		
		// Ground floor.
		int ground = zS - 1;
		for (int y = 0; y < yS; y++) { for (int x = 0; x < xS; x++) {
			int roll = r.nextInt(100);
			TileType tt = TileType.WATER;
			if (roll < 5) {
				tt = TileType.BARGE;
			} else if (roll < 20) {
				tt = TileType.ROOFTOP;
			}
			map[ground][y][x] = new Tile(this, tt, x, y, ground);
		}}
		
		// NS Bridges
		for (int y = 0; y < yS; y++) { for (int x = 0; x < xS; x++) {
			if (typeAt(TileType.WATER, x, y, ground) &&
				typeAt(TileType.ROOFTOP, x, y - 1, ground) &&
				typeAt(TileType.ROOFTOP, x, y + 1, ground))
			{
				map[ground][y][x] = new Tile(this, TileType.BRIDGE_NS, x, y, ground);
			}
		}}
		
		// EW Bridges
		for (int y = 0; y < yS; y++) { for (int x = 0; x < xS; x++) {
			if (typeAt(TileType.WATER, x, y, ground) &&
				typeAt(TileType.ROOFTOP, x - 1, y, ground) &&
				typeAt(TileType.ROOFTOP, x + 1, y, ground))
			{
				map[ground][y][x] = new Tile(this, TileType.BRIDGE_EW, x, y, ground);
			}
		}}
		
		int towerX = 2 + r.nextInt(xS - 4 - 5);
		int towerY = 2 + r.nextInt(yS - 4 - 5);
		int towerTop = zS / 3;
		
		for (int z = towerTop; z < zS; z++) {
			for (int y = towerY; y < towerY + 5; y++) {for (int x = towerX; x < towerX + 5; x++) {
				TileType tt = TileType.APARTMENT;
				if (y == towerY + 4) {
					tt = TileType.V_FARM;
				}
				if (x == towerX + 2 || y == towerY + 2) {
					tt = TileType.CORRIDOR;
				}
				if (x == towerX + 2 && y == towerY + 2) {
					tt = TileType.STAIRWELL;
				}
				map[z][y][x] = new Tile(this, tt, x, y, z);
			}}
		}
		
		people.add(new Person(at(towerX + 2, towerY + 2, zS - 1)));
	}
}
