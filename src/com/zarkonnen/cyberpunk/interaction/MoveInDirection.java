package com.zarkonnen.cyberpunk.interaction;

import com.zarkonnen.cyberpunk.Direction;
import com.zarkonnen.cyberpunk.Person;
import com.zarkonnen.cyberpunk.Tile;

public class MoveInDirection extends AbstractInteraction<Tile> {
	public MoveInDirection(Direction dir, Person actor, Tile target) {
		super(actor, target);
		this.dir = dir;
	}
	
	public final Direction dir;
	
	@Override
	public String getName() {
		return "Move " + dir.name();
	}

	@Override
	public String disabledReason() {
		if (!target().passable(dir)) {
			return "That way is blocked.";
		}
		return null;
	}

	@Override
	public String getDescription() {
		return "Use your legs.";
	}

	@Override
	public String run() {
		actor().moveBy(dir);
		return null;
	}
}
