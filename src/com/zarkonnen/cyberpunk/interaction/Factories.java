package com.zarkonnen.cyberpunk.interaction;

import com.zarkonnen.cyberpunk.Person;
import com.zarkonnen.cyberpunk.Tile;
import java.util.ArrayList;
import java.util.List;

public final class Factories {
	private Factories() {}
	public static final List<TileInteractionFactory<?>> TILE_I_FACTORIES = new ArrayList<TileInteractionFactory<?>>();
	static {
		TILE_I_FACTORIES.add(new Scavenge.F());
	}
	
	public static List<TileInteraction> make(Person actor, Tile target) {
		ArrayList<TileInteraction> l = new ArrayList<TileInteraction>();
		for (TileInteractionFactory<?> f : TILE_I_FACTORIES) {
			l.addAll(f.make(actor, target));
		}
		return l;
	}
}
