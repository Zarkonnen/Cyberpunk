package com.zarkonnen.cyberpunk;

import java.io.Serializable;

public class GameState implements Serializable {
	public final WorldMap map;
	public final Person player;

	public GameState(WorldMap map, Person player) {
		this.map = map;
		this.player = player;
	}
	
	public GameState(long seed, int xS, int yS, int zS) {
		this.map = new WorldMap(seed, xS, yS, zS);
		this.player = this.map.people().get(0);
	}
}
