package com.zarkonnen.cyberpunk.interaction;

import com.zarkonnen.cyberpunk.Direction;
import com.zarkonnen.cyberpunk.Person;
import com.zarkonnen.cyberpunk.Tile;
import java.util.Collections;
import java.util.List;

public class Wander extends AbstractInteraction<Tile> {
	public Wander(Person actor, Tile target) {
		super(actor, target);
	}
		
	@Override
	public String getName() {
		return "Wander aimlessly.";
	}

	@Override
	public String disabledReason() {
		return null;
	}

	@Override
	public String getDescription() {
		return "Use your legs.";
	}

	@Override
	public String run() {
		actor().moveBy(Direction.values()[r().nextInt(Direction.values().length)]);
		return "You wander aimlessly.";
	}
	
	public static class F implements InteractionFactory<Tile, Wander> {
		@Override
		public List<Wander> make(Person actor, Tile t) {
			if (actor.isPlayer) { return Collections.emptyList(); }
			return Collections.singletonList(new Wander(actor, t));
		}
	}
}
