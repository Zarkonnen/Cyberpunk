package com.zarkonnen.cyberpunk;

public class Tile {
	public final TileType type;
	public final int x, y, z;

	public Tile(TileType type, int x, int y, int z) {
		this.type = type;
		this.x = x;
		this.y = y;
		this.z = z;
	}
}
