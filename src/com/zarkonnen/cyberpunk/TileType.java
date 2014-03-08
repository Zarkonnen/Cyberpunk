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
	SLUM_BARGE(
		/* color */ new Clr(180, 150, 150),
		/* passable */ EnumSet.of(Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST)
	),
	ROOFTOP(
		/* color */ new Clr(200, 200, 200),
		/* passable */ EnumSet.of(Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST)
	),
	ROOFTOP_SLUM(
		/* color */ new Clr(210, 180, 180),
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
	),
	STORE_ROOM(
		/* color */ new Clr(120, 100, 20),
		/* passable */ EnumSet.of(Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST)
	),
	ROOFTOP_FARM(
		/* color */ new Clr(80, 170, 60),
		/* passable */ EnumSet.of(Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST)
	),
	MARKET(
		/* color */ new Clr(200, 0, 200),
		/* passable */ EnumSet.of(Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST)
	),
	GUN_SHOP(
		/* color */ new Clr(200, 100, 40),
		/* passable */ EnumSet.of(Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST)
	),
	JEWELLERY_SHOP(
		/* color */ new Clr(200, 100, 40),
		/* passable */ EnumSet.of(Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST)
	),
	CLOTHES_SHOP(
		/* color */ new Clr(200, 100, 40),
		/* passable */ EnumSet.of(Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST)
	),
	ART_GALLERY(
		/* color */ new Clr(200, 100, 40),
		/* passable */ EnumSet.of(Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST)
	),
	HARDWARE_SHOP(
		/* color */ new Clr(200, 100, 40),
		/* passable */ EnumSet.of(Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST)
	),
	CLINIC(
		/* color */ new Clr(200, 200, 255),
		/* passable */ EnumSet.of(Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST)
	),
	GANG_HIDEOUT(
		/* color */ new Clr(100, 20, 20),
		/* passable */ EnumSet.of(Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST)
	),
	SERVER_ROOM(
		/* color */ new Clr(100, 200, 220),
		/* passable */ EnumSet.of(Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST)
	),
	HELIPAD(
		/* color */ new Clr(220, 150, 120),
		/* passable */ EnumSet.of(Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST)
	),
	BAR(
		/* color */ new Clr(150, 100, 40),
		/* passable */ EnumSet.of(Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST)
	),
	BACK_ROOM(
		/* color */ new Clr(75, 50, 20),
		/* passable */ EnumSet.of(Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST)
	),
	GAMBLING_PARLOUR(
		/* color */ new Clr(200, 200, 40),
		/* passable */ EnumSet.of(Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST)
	),
	PENTHOUSE(
		/* color */ new Clr(180, 220, 180),
		/* passable */ EnumSet.of(Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST)
	),
	DRUG_LAB(
		/* color */ new Clr(150, 150, 230),
		/* passable */ EnumSet.of(Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST)
	),
	OFFICE(
		/* color */ new Clr(200, 200, 200),
		/* passable */ EnumSet.of(Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST)
	),
	BROTHEL(
		/* color */ new Clr(220, 50, 100),
		/* passable */ EnumSet.of(Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST)
	),
	DOCK(
		/* color */ new Clr(100, 60, 10),
		/* passable */ EnumSet.of(Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST)
	),
	WAREHOUSE_BARGE(
		/* color */ new Clr(150, 150, 40),
		/* passable */ EnumSet.of(Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST)
	),
	MACHINE_ROOM(
		/* color */ new Clr(60, 60, 45),
		/* passable */ EnumSet.of(Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST)
	),
	GENETICS_LAB(
		/* color */ new Clr(200, 255, 230),
		/* passable */ EnumSet.of(Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST)
	),
	DRUG_DEN(
		/* color */ new Clr(100, 20, 100),
		/* passable */ EnumSet.of(Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST)
	),
	SWEATSHOP(
		/* color */ new Clr(230, 210, 200),
		/* passable */ EnumSet.of(Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST)
	);
	
	public final Clr color;
	public final EnumSet<Direction> passable;

	private TileType(Clr color, EnumSet<Direction> passable) {
		this.color = color;
		this.passable = passable;
	}
}
