package com.zarkonnen.cyberpunk;

import com.zarkonnen.cyberpunk.behave.Job;
import com.zarkonnen.cyberpunk.behave.PersonSpawner;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
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
	public static final TileType[] ROOFTOPS_AND_BARGES = {
		TileType.ROOFTOP,
		TileType.ROOFTOP_FARM,
		TileType.ROOFTOP_SLUM,
		TileType.MARKET,
		TileType.BROTHEL,
		TileType.SWEATSHOP,
		TileType.SLUM_BARGE,
		TileType.WAREHOUSE_BARGE
	};
	public static final TileType[] BRIDGES = {
		TileType.BRIDGE_NS,
		TileType.BRIDGE_EW,
	};
	public static final TileType[] ROOFTOPS_DOCKS_AND_BRIDGES = {
		TileType.ROOFTOP,
		TileType.ROOFTOP_FARM,
		TileType.ROOFTOP_SLUM,
		TileType.MARKET,
		TileType.BROTHEL,
		TileType.SWEATSHOP,
		TileType.BRIDGE_NS,
		TileType.BRIDGE_EW,
		TileType.DOCK,
		TileType.SLUM_BARGE,
		TileType.WAREHOUSE_BARGE
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
	public HashSet<Item> knownData = new HashSet<Item>();
	public int time = 30;
	public static final int DAY_LENGTH = 6 * 24;
	
	public final int towerX, towerY;
	
	public int timeOfDay() {
		return time % DAY_LENGTH;
	}
	
	public int hour() {
		return (time % DAY_LENGTH) / 6;
	}
	
	public int itemQuantity(ItemType t) {
		int q = 0;
		for (Person p : people) {
			for (Item item : p.inventory) {
				if (item.type == t) { q++; }
			}
		}
		for (int z = 0; z < map.length; z++) { for (int y = 0; y < map[0].length; y++) { for (int x = 0; x < map[0][0].length; x++) {
			for (Tile.HiddenItem hitem : map[z][y][x].hiddenItems) {
				if (hitem.item.type == t) { q++; }
			}
		}}}
		return q;
	}
	
	public int totalItemsQuantity() {
		int q = 0;
		for (Person p : people) { q += p.inventory.size(); }
		for (int z = 0; z < map.length; z++) { for (int y = 0; y < map[0].length; y++) { for (int x = 0; x < map[0][0].length; x++) {
			q += map[z][y][x].inventory.size() + map[z][y][x].gadgets.size() + map[z][y][x].hiddenItems.size();
		}}}

		return q;
	}
	
	public void tick() {
		time++;
		for (Iterator<Person> it = people.iterator(); it.hasNext();) {
			if (it.next().tick()) {
				it.remove();
			}
		}
		for (int z = 0; z < map.length; z++) { for (int y = 0; y < map[0].length; y++) { for (int x = 0; x < map[0][0].length; x++) {
			Tile t = map[z][y][x];
			int sz = t.hiddenItems.size();
			for (int i = 0; i < sz;) {
				Item item = t.hiddenItems.get(i).item;
				if (item.type.maxAge > 0 && item.age++ > item.type.maxAge) {
					t.hiddenItems.remove(i);
					sz--;
				} else {
					i++;
				}
			}
		}}}
	}
	
	public boolean test(int successChance) {
		return r.nextInt(100) < successChance;
	}
		
	public List<Person> people() {
		return people;
	}
	
	public boolean atMapEdge(int x, int y) {
		return (x == 0 || x == map[0][0].length - 1) || (y == 0 || y == map[0].length - 1);
	}
	
	public Tile at(int x, int y, int z) {
		if (z < 0 || z >= map.length || y < 0 || y >= map[0].length || x < 0 || x >= map[0][0].length) {
			return new Tile(this, TileType.NOTHING, x, y, z);
		}
		return map[z][y][x];
	}
	
	public ArrayList<Tile> tilesOfType(TileType tt) {
		ArrayList<Tile> ts = new ArrayList<Tile>();
		for (int z = 0; z < map.length; z++) { for (int y = 0; y < map[0].length; y++) { for (int x = 0; x < map[0][0].length; x++) {
			if (map[z][y][x].type == tt) {
				ts.add(map[z][y][x]);
			}
		}}}
		return ts;
	}
	
	public Tile randomOfType(TileType tt) {
		ArrayList<Tile> ts = tilesOfType(tt);
		Collections.shuffle(ts, r);
		if (ts.isEmpty()) { return null; }
		return ts.get(0);
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
			if (roll < 10) {
				tt = r(BARGES);
			} else if (roll < 20) {
				tt = r(ROOFTOPS_AND_BARGES);
			}
			map[ground][y][x] = new Tile(this, tt, x, y, ground);
		}}
		
		// NS Bridges
		for (int y = 0; y < yS; y++) { for (int x = 0; x < xS; x++) {
			if (typeAt(TileType.WATER, x, y, ground) &&
				typeAt(ROOFTOPS_AND_BARGES, x, y - 1, ground) &&
				typeAt(ROOFTOPS_AND_BARGES, x, y + 1, ground))
			{
				map[ground][y][x] = new Tile(this, TileType.BRIDGE_NS, x, y, ground);
			}
		}}
		
		// EW Bridges
		for (int y = 0; y < yS; y++) { for (int x = 0; x < xS; x++) {
			if (typeAt(TileType.WATER, x, y, ground) &&
				typeAt(ROOFTOPS_AND_BARGES, x - 1, y, ground) &&
				typeAt(ROOFTOPS_AND_BARGES, x + 1, y, ground))
			{
				map[ground][y][x] = new Tile(this, TileType.BRIDGE_EW, x, y, ground);
			}
		}}
		
		// Bridge cross
		for (int x = 0; x < xS; x++) {
			if (typeAt(TileType.WATER, x, yS / 2, ground)) {
				map[ground][yS / 2][x] = new Tile(this, TileType.BRIDGE_EW, x, yS / 2, ground);
			}
		}
		for (int y = 0; y < yS; y++) {
			if (typeAt(TileType.WATER, xS / 2, y, ground)) {
				map[ground][y][xS / 2] = new Tile(this, TileType.BRIDGE_NS, xS / 2, y, ground);
			}
		}
		
		towerX = 2 + r.nextInt(xS - 4 - 5);
		towerY = 2 + r.nextInt(yS - 4 - 5);
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
			}}
		}
		
		// Docks
		for (int y = towerY - 1; y < towerY + 6; y++) {for (int x = towerX - 1; x < towerX + 6; x++) {
			if (typeAt(TileType.WATER, x, y, ground) && r.nextInt(3) == 0) {
				map[ground][y][x] = new Tile(this, TileType.DOCK, x, y, ground);
			}
		}}
		
		// Bridge x-ings are slums
		for (int y = 0; y < yS; y++) { for (int x = 0; x < xS; x++) {
			if (typeAt(BRIDGES, x, y, ground)) {
				if (
					(typeAt(ROOFTOPS_DOCKS_AND_BRIDGES, x - 1, y, ground) || typeAt(ROOFTOPS_DOCKS_AND_BRIDGES, x + 1, y, ground)) &&
					(typeAt(ROOFTOPS_DOCKS_AND_BRIDGES, x, y - 1, ground) || typeAt(ROOFTOPS_DOCKS_AND_BRIDGES, x, y + 1, ground))
				) {
					map[ground][y][x] = new Tile(this, TileType.ROOFTOP_SLUM, x, y, ground);
				}
			}
		}}
		
		// Use flood fill to turn disconnected stuff back into water.
		for (int z = 0; z < map.length; z++) { for (int y = 0; y < map[0].length; y++) { for (int x = 0; x < map[0][0].length; x++) {
			map[z][y][x].pathDist = Integer.MAX_VALUE;
		}}}
		at(towerX, towerY, ground).pathDist = 0;
		LinkedList<Tile> q = new LinkedList<Tile>();
		q.add(at(towerX, towerY, ground));
		floodFillAndKillDisconnecteds(q, ground);
		
		// Locking
		for (int z = 0; z < map.length; z++) { for (int y = 0; y < map[0].length; y++) { for (int x = 0; x < map[0][0].length; x++) {
			map[z][y][x].locked = map[z][y][x].type.locked > 0;
			map[z][y][x].hackInResistance = (int) ((0.5 + r.nextDouble()) * map[z][y][x].type.locked);
			map[z][y][x].breakInResistance = (int) ((0.5 + r.nextDouble()) * map[z][y][x].type.locked);
		}}}
		
		ItemSpawner.installAll(this);
		PersonSpawner.installAll(this);
		
		// DO THIS LAST
		calcPathsTowardsEdge();		
	}
	
	public Person makePlayer(CharacterClass playerCC) {
		Tile homeT = tilesOfType(TileType.APARTMENT).isEmpty() ? null : tilesOfType(TileType.APARTMENT).get(0);
		if (homeT == null) {
			if (!tilesOfType(TileType.ROOFTOP_SLUM).isEmpty()) {
				homeT = tilesOfType(TileType.ROOFTOP_SLUM).get(0);
			} else if (!tilesOfType(TileType.SLUM_BARGE).isEmpty()) {
				homeT = tilesOfType(TileType.SLUM_BARGE).get(0);
			} else {
				homeT = tilesOfType(TileType.CORRIDOR).get(0);
			}
		}
		Person player = new Person(homeT);
		player.home = homeT;
		playerCC.install(player, r);
		people.add(player);
		player.isPlayer = true;
		return player;
	}
	
	private void calcPathsTowardsEdge() {
		LinkedList<Tile> q = new LinkedList<Tile>();
		for (int z = 0; z < map.length; z++) { for (int y = 0; y < map[0].length; y++) { for (int x = 0; x < map[0][0].length; x++) {
			if (atMapEdge(x, y) && z == map.length - 1) {
				q.add(map[z][y][x]);
				map[z][y][x].pathDist = 0;
			} else {
				map[z][y][x].pathDist = Integer.MAX_VALUE;
			}
			map[z][y][x].bestPath = null;
		}}}
		floodFillAndSetTowards(q, null, null);
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
	
	private void floodFillAndKillDisconnecteds(LinkedList<Tile> q, int ground) {
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
						if (!qs.contains(t2)) {
							qs.add(t2);
							q.add(t2);
						}
					}
				}
			}
		}
		
		for (int y = 0; y < map[0].length; y++) { for (int x = 0; x < map[0][0].length; x++) {
			if (map[ground][y][x].pathDist == Integer.MAX_VALUE) {
				map[ground][y][x] = new Tile(this, TileType.WATER, x, y, ground);
			}
		}}
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
		if (forType == null && forTile == null) {
			for (int z = 0; z < map.length; z++) { for (int y = 0; y < map[0].length; y++) { for (int x = 0; x < map[0][0].length; x++) {
				map[z][y][x].towardsEdge = map[z][y][x].bestPath;
			}}}
		}
	}

	public void addPerson(Person p) {
		people.add(p);
	}
}
