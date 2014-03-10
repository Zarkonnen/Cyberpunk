package com.zarkonnen.cyberpunk.interaction;

import com.zarkonnen.cyberpunk.Person;
import com.zarkonnen.cyberpunk.Tile;
import com.zarkonnen.cyberpunk.TileType;
import java.util.Collections;
import java.util.List;

public abstract class TileTypeFactory implements InteractionFactory<Tile, Interaction<Tile>> {
	public final TileType type;

	public TileTypeFactory(TileType t) {
		this.type = t;
	}
	
	public abstract Interaction<Tile> get(Person actor, Tile t);
	
	@Override
	public List<Interaction<Tile>> make(Person actor, Tile t) {
		if (t.type == type) {
			Interaction<Tile> i = get(actor, t);
			if (i == null) {
				return Collections.emptyList();
			} else {
				return Collections.singletonList(i);
			}
		} else {
			return Collections.emptyList();
		}
	}
}
