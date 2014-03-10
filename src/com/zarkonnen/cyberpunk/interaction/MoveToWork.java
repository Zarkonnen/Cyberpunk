package com.zarkonnen.cyberpunk.interaction;

import com.zarkonnen.cyberpunk.Person;
import com.zarkonnen.cyberpunk.Tile;
import java.util.Collections;
import java.util.List;

public class MoveToWork extends AbstractInteraction<Tile> {
	public MoveToWork(Person actor, Tile target) {
		super(actor, target);
	}

	@Override
	public String getName() {
		return "Move towards your workplace.";
	}

	@Override
	public String disabledReason() {
		if (actor().workplace == null) {
			return "You have no workplace.";
		}
		if (actor().location().towardsTile(actor().workplace) == null) {
			return "No path to workplace.";
		}
		return null;
	}

	@Override
	public String getDescription() {
		return "Off to work you go.";
	}

	@Override
	public String run() {
		actor().moveTo(actor().location().towardsTile(actor().workplace));
		return "You move towards work.";
	}
	
	public static class F implements InteractionFactory<Tile, MoveToWork> {
		@Override
		public List<MoveToWork> make(Person actor, Tile t) {
			if (actor.workplace == null) { return Collections.emptyList(); }
			if (actor.isPlayer) { return Collections.emptyList(); }
			return Collections.singletonList(new MoveToWork(actor, t));
		}
	}
}
