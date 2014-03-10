package com.zarkonnen.cyberpunk.interaction;

import com.zarkonnen.cyberpunk.Person;
import com.zarkonnen.cyberpunk.Tile;
import java.util.Collections;
import java.util.List;

public class MoveToMapEdge extends AbstractInteraction<Tile> {
	public MoveToMapEdge(Person actor, Tile target) {
		super(actor, target);
	}

	@Override
	public String getName() {
		return "Move to the map edge.";
	}

	@Override
	public String disabledReason() {
		return null;
	}

	@Override
	public String getDescription() {
		return "Off to the edge you go.";
	}

	@Override
	public String run() {
		actor().moveTo(actor().location().towardsEdge());
		return "You move towards the map edge.";
	}
	
	public static class F implements InteractionFactory<Tile, MoveToMapEdge> {
		@Override
		public List<MoveToMapEdge> make(Person actor, Tile t) {
			if (actor.isPlayer) { return Collections.emptyList(); }
			return Collections.singletonList(new MoveToMapEdge(actor, t));
		}
	}
}
