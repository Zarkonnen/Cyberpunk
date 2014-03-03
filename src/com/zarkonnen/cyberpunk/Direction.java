package com.zarkonnen.cyberpunk;

import java.io.Serializable;

public enum Direction implements Serializable{
	NORTH(0, -1, 0),
	SOUTH(0, 1, 0),
	EAST(1, 0, 0),
	WEST(-1, 0, 0),
	UP(0, 0, -1),
	DOWN(0, 0, 1);
		
	public final int dx, dy, dz;

	private Direction(int dx, int dy, int dz) {
		this.dx = dx;
		this.dy = dy;
		this.dz = dz;
	}
	
	public static Direction reverse(Direction d) {
		switch(d) {
			case NORTH: return SOUTH;
			case SOUTH: return NORTH;
			case EAST: return WEST;
			case WEST: return EAST;
			case UP: return DOWN;
			case DOWN: return UP;
			default: return null;
		}
	}
}
