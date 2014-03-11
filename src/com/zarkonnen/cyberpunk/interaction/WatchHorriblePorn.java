package com.zarkonnen.cyberpunk.interaction;

import com.zarkonnen.cyberpunk.Person;
import com.zarkonnen.cyberpunk.Tile;
import java.util.Collections;
import java.util.List;

public class WatchHorriblePorn extends AbstractInteraction<Tile> {
	public WatchHorriblePorn(Person actor, Tile target) {
		super(actor, target);
	}
	
	@Override
	public String getName() {
		return "Watch horrible, disgusting porn";
	}

	@Override
	public String disabledReason() {
		return null;
	}

	@Override
	public String getDescription() {
		return "Watch horrible, disgusting porn.";
	}

	@Override
	public String run() {
		return null;
	}

	public static class F implements InteractionFactory<Tile, WatchHorriblePorn> {
		@Override
		public List<WatchHorriblePorn> make(Person actor, Tile t) {
			if (actor.isPlayer || t != actor.home) {
				return Collections.emptyList();
			}
			return Collections.singletonList(new WatchHorriblePorn(actor, t));
		}
	}
}
