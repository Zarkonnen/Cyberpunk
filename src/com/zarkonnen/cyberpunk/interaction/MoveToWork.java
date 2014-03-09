package com.zarkonnen.cyberpunk.interaction;

import com.zarkonnen.cyberpunk.Person;
import com.zarkonnen.cyberpunk.Tile;
import com.zarkonnen.cyberpunk.TileType;

public class MoveToWork extends AbstractInteraction<Tile> {
	public MoveToWork(Person actor, Tile target) {
		super(actor, target);
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
