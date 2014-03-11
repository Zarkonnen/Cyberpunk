package com.zarkonnen.cyberpunk.interaction;

import com.zarkonnen.cyberpunk.Person;
import com.zarkonnen.cyberpunk.Tile;
import java.util.Collections;
import java.util.List;

public class StealFromEmployers extends AbstractInteraction<Tile> {
	public StealFromEmployers(Person actor, Tile target) {
		super(actor, target);
	}
	
	@Override
	public String getName() {
		return "Secretly steal from employers";
	}

	@Override
	public String disabledReason() {
		return null;
	}

	@Override
	public String getDescription() {
		return "Secretly steal from employers.";
	}

	@Override
	public String run() {
		actor().money += 40;
		return null;
	}

	public static class F implements InteractionFactory<Tile, StealFromEmployers> {
		@Override
		public List<StealFromEmployers> make(Person actor, Tile t) {
			if (actor.isPlayer || t != actor.workplace) {
				return Collections.emptyList();
			}
			return Collections.singletonList(new StealFromEmployers(actor, t));
		}
	}
}
