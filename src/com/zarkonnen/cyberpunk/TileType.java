package com.zarkonnen.cyberpunk;

import com.zarkonnen.catengine.util.Clr;
import java.io.Serializable;
import java.util.EnumSet;

public enum TileType implements Serializable {
	NOTHING(
		/* color */ Clr.BLACK,
		/* passable */ EnumSet.noneOf(Direction.class)
	),
	AIR(
		/* color */ Clr.WHITE,
		/* passable */ EnumSet.noneOf(Direction.class)
	),
	WATER(
		/* color */ new Clr(150, 160, 200),
		/* passable */ EnumSet.noneOf(Direction.class)
	),
	BARGE(
		/* color */ new Clr(180, 150, 150),
		/* passable */ EnumSet.of(Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST)
	),
	ROOFTOP(
		/* color */ new Clr(200, 200, 200),
		/* passable */ EnumSet.of(Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST)
	),
	BRIDGE_NS(
		/* color */ new Clr(150, 130, 30),
		/* passable */ EnumSet.of(Direction.NORTH, Direction.SOUTH)
	),
	BRIDGE_EW(
		/* color */ new Clr(150, 130, 30),
		/* passable */ EnumSet.of(Direction.EAST, Direction.WEST)
	),
	CORRIDOR(
		/* color */ new Clr(50, 50, 50),
		/* passable */ EnumSet.of(Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST)
	),
	STAIRWELL(
		/* color */ new Clr(20, 20, 20),
		/* passable */ EnumSet.allOf(Direction.class)
	),
	APARTMENT(
		/* color */ Clr.fromHex("c6c4b1"),
		/* passable */ EnumSet.of(Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST)
	),
	V_FARM(
		/* color */ new Clr(80, 200, 60),
		/* passable */ EnumSet.of(Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST)
	);
	
	public final Clr color;
	public final EnumSet<Direction> passable;

	private TileType(Clr color, EnumSet<Direction> passable) {
		this.color = color;
		this.passable = passable;
	}
}
