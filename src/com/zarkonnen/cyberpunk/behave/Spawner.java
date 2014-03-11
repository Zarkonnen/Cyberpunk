package com.zarkonnen.cyberpunk.behave;

import com.zarkonnen.cyberpunk.Person;
import com.zarkonnen.cyberpunk.Tile;
import com.zarkonnen.cyberpunk.TileType;
import com.zarkonnen.cyberpunk.WorldMap;

public enum Spawner {
	;
	public final int quantity;
	public final TileType work;
	public final Job job;
	public final TileType home;
	public final TileType supply;
	public final int cash;

	private Spawner(int quantity, TileType work, Job job, TileType home, TileType supply, int cash) {
		this.quantity = quantity;
		this.work = work;
		this.job = job;
		this.home = home;
		this.supply = supply;
		this.cash = cash;
	}
	
	public void install(WorldMap wm) {
		for (Tile workT : wm.tilesOfType(work)) {
			for (int i = 0; i < quantity; i++) {
				Tile homeT;
				if (work == home) {
					homeT = workT;
				} else {
					homeT = wm.randomOfType(home);
				}
				Tile supplyT = null;
				if (supply != null) {
					supplyT = wm.randomOfType(supply);
				}
				Person p = new Person(homeT);
				p.home = homeT;
				p.workplace = workT;
				p.supplier = supplyT;
				job.doInstall(p, wm.r);
				p.money = cash;
				wm.addPerson(p);
			}
		}
	}
	
	public static void installAll(WorldMap wm) {
		for (Spawner sp : Spawner.values()) {
			sp.install(wm);
		}
	}
}
