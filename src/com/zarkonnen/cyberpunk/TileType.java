package com.zarkonnen.cyberpunk;

import com.zarkonnen.catengine.util.Clr;
import java.io.Serializable;
import java.util.EnumSet;

public enum TileType implements Serializable {
	NOTHING(
		/* color */ Clr.BLACK,
		/* passable */ EnumSet.noneOf(Direction.class),
		/* locked */ 0
	),
	AIR(
		/* color */ Clr.DARK_GREY,
		/* passable */ EnumSet.noneOf(Direction.class),
		/* locked */ 0
	),
	WATER(
		/* color */ new Clr(150, 160, 200),
		/* passable */ EnumSet.noneOf(Direction.class),
		/* locked */ 0
	),
	SLUM_BARGE(
		/* color */ new Clr(180, 150, 150),
		/* passable */ EnumSet.of(Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST),
		/* locked */ 0
	),
	ROOFTOP(
		/* color */ new Clr(200, 200, 200),
		/* passable */ EnumSet.of(Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST),
		/* locked */ 0
	),
	ROOFTOP_SLUM(
		/* color */ new Clr(210, 180, 180),
		/* passable */ EnumSet.of(Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST),
		/* locked */ 0
	),
	BRIDGE_NS(
		/* color */ new Clr(150, 130, 30),
		/* passable */ EnumSet.of(Direction.NORTH, Direction.SOUTH),
		/* locked */ 0
	),
	BRIDGE_EW(
		/* color */ new Clr(150, 130, 30),
		/* passable */ EnumSet.of(Direction.EAST, Direction.WEST),
		/* locked */ 0
	),
	CORRIDOR(
		/* color */ new Clr(50, 50, 50),
		/* passable */ EnumSet.of(Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST),
		/* locked */ 0
	),
	STAIRWELL(
		/* color */ new Clr(20, 20, 20),
		/* passable */ EnumSet.allOf(Direction.class),
		/* locked */ 0
	),
	APARTMENT(
		/* color */ Clr.fromHex("c6c4b1"),
		/* passable */ EnumSet.of(Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST),
		/* locked */ 0
	),
	V_FARM(
		/* color */ new Clr(80, 200, 60),
		/* passable */ EnumSet.of(Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST),
		/* locked */ 30
	),
	STORE_ROOM(
		/* color */ new Clr(120, 100, 20),
		/* passable */ EnumSet.of(Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST),
		/* locked */ 70
	),
	ROOFTOP_FARM(
		/* color */ new Clr(80, 170, 60),
		/* passable */ EnumSet.of(Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST),
		/* locked */ 0
	),
	MARKET(
		/* color */ new Clr(200, 0, 200),
		/* passable */ EnumSet.of(Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST),
		/* locked */ 0
	),
	GUN_SHOP(
		/* color */ new Clr(200, 100, 40),
		/* passable */ EnumSet.of(Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST),
		/* locked */ 80
	),
	JEWELLERY_SHOP(
		/* color */ new Clr(200, 100, 40),
		/* passable */ EnumSet.of(Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST),
		/* locked */ 80
	),
	CLOTHES_SHOP(
		/* color */ new Clr(200, 100, 40),
		/* passable */ EnumSet.of(Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST),
		/* locked */ 50
	),
	ART_GALLERY(
		/* color */ new Clr(200, 100, 40),
		/* passable */ EnumSet.of(Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST),
		/* locked */ 60
	),
	HARDWARE_SHOP(
		/* color */ new Clr(200, 100, 40),
		/* passable */ EnumSet.of(Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST),
		/* locked */ 70
	),
	CLINIC(
		/* color */ new Clr(200, 200, 255),
		/* passable */ EnumSet.of(Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST),
		/* locked */ 50
	),
	GANG_HIDEOUT(
		/* color */ new Clr(100, 20, 20),
		/* passable */ EnumSet.of(Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST),
		/* locked */ 0
	),
	SERVER_ROOM(
		/* color */ new Clr(100, 200, 220),
		/* passable */ EnumSet.of(Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST),
		/* locked */ 60
	),
	HELIPAD(
		/* color */ new Clr(220, 150, 120),
		/* passable */ EnumSet.of(Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST),
		/* locked */ 0
	),
	BAR(
		/* color */ new Clr(150, 100, 40),
		/* passable */ EnumSet.of(Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST),
		/* locked */ 0
	),
	BACK_ROOM(
		/* color */ new Clr(75, 50, 20),
		/* passable */ EnumSet.of(Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST),
		/* locked */ 0
	),
	GAMBLING_PARLOUR(
		/* color */ new Clr(200, 200, 40),
		/* passable */ EnumSet.of(Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST),
		/* locked */ 0
	),
	PENTHOUSE(
		/* color */ new Clr(180, 220, 180),
		/* passable */ EnumSet.of(Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST),
		/* locked */ 60
	),
	DRUG_LAB(
		/* color */ new Clr(150, 150, 230),
		/* passable */ EnumSet.of(Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST),
		/* locked */ 50
	),
	OFFICE(
		/* color */ new Clr(200, 200, 200),
		/* passable */ EnumSet.of(Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST),
		/* locked */ 40
	),
	BROTHEL(
		/* color */ new Clr(220, 50, 100),
		/* passable */ EnumSet.of(Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST),
		/* locked */ 0
	),
	DOCK(
		/* color */ new Clr(100, 60, 10),
		/* passable */ EnumSet.of(Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST),
		/* locked */ 0
	),
	WAREHOUSE_BARGE(
		/* color */ new Clr(150, 150, 40),
		/* passable */ EnumSet.of(Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST),
		/* locked */ 20
	),
	MACHINE_ROOM(
		/* color */ new Clr(60, 60, 45),
		/* passable */ EnumSet.of(Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST),
		/* locked */ 40
	),
	GENETICS_LAB(
		/* color */ new Clr(200, 255, 230),
		/* passable */ EnumSet.of(Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST),
		/* locked */ 50
	),
	DRUG_DEN(
		/* color */ new Clr(100, 20, 100),
		/* passable */ EnumSet.of(Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST),
		/* locked */ 0
	),
	SWEATSHOP(
		/* color */ new Clr(230, 210, 200),
		/* passable */ EnumSet.of(Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST),
		/* locked */ 20
	);
	
	public final Clr color;
	public final EnumSet<Direction> passable;
	public final int locked;

	private TileType(Clr color, EnumSet<Direction> passable, int locked) {
		this.color = color;
		this.passable = passable;
		this.locked = locked;
	}
}
