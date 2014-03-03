package com.zarkonnen.cyberpunk;

import com.zarkonnen.catengine.util.Clr;
import java.io.Serializable;

public enum TileType implements Serializable {
	NOTHING(
		/* color */ Clr.BLACK
	),
	AIR(
		/* color */ Clr.WHITE
	),
	WATER(
		/* color */ new Clr(150, 160, 200)
	),
	BARGE(
		/* color */ new Clr(180, 150, 150)
	),
	ROOFTOP(
		/* color */ new Clr(200, 200, 200)
	),
	BRIDGE_NS(
		/* color */ new Clr(150, 130, 30)
	),
	BRIDGE_EW(
		/* color */ new Clr(150, 130, 30)
	),
	CORRIDOR(
		/* color */ new Clr(50, 50, 50)
	),
	STAIRWELL(
		/* color */ new Clr(20, 20, 20)
	),
	APARTMENT(
		/* color */ Clr.fromHex("c6c4b1")
	),
	V_FARM(
		/* color */ new Clr(80, 200, 60)
	);
	
	public final Clr color;

	private TileType(Clr color) {
		this.color = color;
	}
}
