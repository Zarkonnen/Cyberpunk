package com.zarkonnen.cyberpunk.interaction;

import com.zarkonnen.cyberpunk.Person;
import com.zarkonnen.cyberpunk.Tile;
import java.util.Collections;
import java.util.List;

public class MoveToHome extends AbstractInteraction<Tile> {
	public MoveToHome(Person actor, Tile target) {
		super(actor, target);
	}

	@Override
	public String getName() {
		return "Move towards your home.";
	}

	@Override
	public String disabledReason() {
		if (actor().home == null) {
			return "You have no home.";
		}
		if (actor().location().towardsTile(actor().home) == null) {
			return "No path to home.";
		}
		return null;
	}

	@Override
	public String getDescription() {
		return "Off home you go.";
	}

	@Override
	public String run() {
		actor().moveTo(actor().location().towardsTile(actor().home));
		return "You move towards home.";
	}
	
	public static class F implements InteractionFactory<Tile, MoveToHome> {
		@Override
		public List<MoveToHome> make(Person actor, Tile t) {
			if (actor.home == null) { return Collections.emptyList(); }
			if (actor.isPlayer) { return Collections.emptyList(); }
			return Collections.singletonList(new MoveToHome(actor, t));
		}
	}
}
