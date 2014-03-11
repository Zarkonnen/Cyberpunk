package com.zarkonnen.cyberpunk.interaction;

import com.zarkonnen.cyberpunk.Person;
import com.zarkonnen.cyberpunk.Tile;
import java.util.Collections;
import java.util.List;

public class Adultery extends AbstractInteraction<Tile> {
	public Adultery(Person actor, Tile target) {
		super(actor, target);
	}
	
	@Override
	public String getName() {
		return "Commit adultery";
	}

	@Override
	public String disabledReason() {
		return null;
	}

	@Override
	public String getDescription() {
		return "Commit adultery.";
	}

	@Override
	public String run() {
		return null;
	}

	public static class F implements InteractionFactory<Tile, Adultery> {
		@Override
		public List<Adultery> make(Person actor, Tile t) {
			if (actor.isPlayer || t != actor.home) {
				return Collections.emptyList();
			}
			return Collections.singletonList(new Adultery(actor, t));
		}
	}
}
