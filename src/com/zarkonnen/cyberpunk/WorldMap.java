package com.zarkonnen.cyberpunk;

import java.io.Serializable;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public final class WorldMap implements Serializable {
	public static final TileType[] TOWER_ROOMS = {
		TileType.APARTMENT,
		TileType.ART_GALLERY,
		TileType.BACK_ROOM,
		TileType.BAR,
		TileType.BROTHEL,
		TileType.CLINIC,
		TileType.CLOTHES_SHOP,
		TileType.DRUG_DEN,
		TileType.DRUG_LAB,
		TileType.GAMBLING_PARLOUR,
		TileType.GANG_HIDEOUT,
		TileType.GENETICS_LAB,
		TileType.GUN_SHOP,
		TileType.HARDWARE_SHOP,
		TileType.JEWELLERY_SHOP,
		TileType.MACHINE_ROOM,
		TileType.MARKET,
		TileType.OFFICE,
		TileType.SERVER_ROOM,
		TileType.STORE_ROOM,
		TileType.SWEATSHOP
	};
	public static final TileType[] BARGES = {
		TileType.SLUM_BARGE,
		TileType.WAREHOUSE_BARGE
	};
	public static final TileType[] ROOFTOPS = {
		TileType.ROOFTOP,
		TileType.ROOFTOP_FARM,
		TileType.ROOFTOP_SLUM,
		TileType.MARKET,
		TileType.BROTHEL,
		TileType.SWEATSHOP
	};
	public static final TileType[] TOWER_ROOFTOPS = {
		TileType.HELIPAD,
		TileType.PENTHOUSE,
		TileType.PENTHOUSE,
		TileType.PENTHOUSE,
		TileType.PENTHOUSE
	};
	private final Tile[][][] map;
	private final LinkedList<Person> people = new LinkedList<Person>();
	public final Random r;
	public int time = 0;
	public static final int DAY_LENGTH = 6 * 24;
	
	public int timeOfDay() {
		return time % DAY_LENGTH;
	}
	
	public int hour() {
		return (time % DAY_LENGTH) / 6;
	}
	
	public void tick() {
		time++;
		for (Person p : people) {
			p.tick();
		}
	}
	
	public boolean test(int successChance) {
		return r.nextInt(100) < successChance;
	}
		
	public List<Person> people() {
		return people;
	}
	
	public boolean atMapEdge(int x, int y) {
		return (x == 0 || x == map[0][0].length - 1) && (y == 0 || y == map[0].length - 1);
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
	
	public boolean typeAt(TileType[] ts, int x, int y, int z) {
		TileType tp = at(x, y, z).type;
		for (TileType tt : ts) {
			if (tt == tp) { return true; }
		}
		return false;
	}
	
	private TileType r(TileType[] ts) {
		return ts[r.nextInt(ts.length)];
	}
	
	public WorldMap(long seed, int xS, int yS, int zS) {
		r = new Random(seed);
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
				tt = r(BARGES);
			} else if (roll < 20) {
				tt = r(ROOFTOPS);
			}
			map[ground][y][x] = new Tile(this, tt, x, y, ground);
		}}
		
		// NS Bridges
		for (int y = 0; y < yS; y++) { for (int x = 0; x < xS; x++) {
			if (typeAt(TileType.WATER, x, y, ground) &&
				typeAt(ROOFTOPS, x, y - 1, ground) &&
				typeAt(ROOFTOPS, x, y + 1, ground))
			{
				map[ground][y][x] = new Tile(this, TileType.BRIDGE_NS, x, y, ground);
			}
		}}
		
		// EW Bridges
		for (int y = 0; y < yS; y++) { for (int x = 0; x < xS; x++) {
			if (typeAt(TileType.WATER, x, y, ground) &&
				typeAt(ROOFTOPS, x - 1, y, ground) &&
				typeAt(ROOFTOPS, x + 1, y, ground))
			{
				map[ground][y][x] = new Tile(this, TileType.BRIDGE_EW, x, y, ground);
			}
		}}
		
		int towerX = 2 + r.nextInt(xS - 4 - 5);
		int towerY = 2 + r.nextInt(yS - 4 - 5);
		int towerTop = zS / 3;
		
		for (int z = towerTop; z < zS; z++) {
			for (int y = towerY; y < towerY + 5; y++) {for (int x = towerX; x < towerX + 5; x++) {
				TileType tt = z == towerTop ? r(TOWER_ROOFTOPS) : r(TOWER_ROOMS);
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
				map[z][y][x].hiddenItems.add(new Tile.HiddenItem(r.nextInt(30), new Item(ItemType.RAM)));
				map[z][y][x].hiddenItems.add(new Tile.HiddenItem(r.nextInt(10), new Item(ItemType.SNACKS)));
			}}
		}
		
		people.add(new Person(at(towerX + 2, towerY + 2, zS - 1)));
	}

	public void calcPathsFor(Tile t) {
		LinkedList<Tile> q = new LinkedList<Tile>();
		for (int z = 0; z < map.length; z++) { for (int y = 0; y < map[0].length; y++) { for (int x = 0; x < map[0][0].length; x++) {
			map[z][y][x].pathDist = Integer.MAX_VALUE;
			map[z][y][x].bestPath = null;
		}}}
		t.pathDist = 0;
		q.add(t);
		floodFillAndSetTowards(q, null, t);
	}
	
	public void calcPathsFor(TileType tt) {
		LinkedList<Tile> q = new LinkedList<Tile>();
		for (int z = 0; z < map.length; z++) { for (int y = 0; y < map[0].length; y++) { for (int x = 0; x < map[0][0].length; x++) {
			map[z][y][x].bestPath = null;
			if (map[z][y][x].type == tt) {
				map[z][y][x].pathDist = 0;
				q.add(map[z][y][x]);
			} else {
				map[z][y][x].pathDist = Integer.MAX_VALUE;
			}
		}}}
		floodFillAndSetTowards(q, tt, null);
	}
	
	private void floodFillAndSetTowards(LinkedList<Tile> q, TileType forType, Tile forTile) {
		HashSet<Tile> qs = new HashSet<Tile>();
		qs.addAll(q);
		while (!q.isEmpty()) {
			Tile t = q.pollFirst();
			qs.remove(t);
			for (Direction dir : Direction.values()) {
				if (t.passable(dir)) {
					Tile t2 = t.in(dir);
					int newDist = t.pathDist + 1;
					if (t2.pathDist > newDist) {
						t2.pathDist = newDist;
						t2.bestPath = t;
						if (!qs.contains(t2)) {
							qs.add(t2);
							q.add(t2);
						}
					}
				}
			}
		}
		
		if (forType != null) {
			for (int z = 0; z < map.length; z++) { for (int y = 0; y < map[0].length; y++) { for (int x = 0; x < map[0][0].length; x++) {
				map[z][y][x].towardsTileType.put(forType, map[z][y][x].bestPath);
			}}}
		}
		if (forTile != null) {
			for (int z = 0; z < map.length; z++) { for (int y = 0; y < map[0].length; y++) { for (int x = 0; x < map[0][0].length; x++) {
				map[z][y][x].towardsTile.put(forTile, map[z][y][x].bestPath);
			}}}
		}
	}
}
