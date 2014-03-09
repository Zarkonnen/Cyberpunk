package com.zarkonnen.cyberpunk.interaction;

import com.zarkonnen.cyberpunk.Person;
import com.zarkonnen.cyberpunk.Tile;
import com.zarkonnen.cyberpunk.TileType;

public class MoveToType extends AbstractInteraction<Tile> {
	public final TileType type;

	public MoveToType(TileType type, Person actor, Tile target) {
		super(actor, target);
		this.type = type;
	}

	@Override
	public String getName() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public String disabledReason() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public String getDescription() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public String run() {
		throw new UnsupportedOperationException("Not supported yet.");
	}
}
