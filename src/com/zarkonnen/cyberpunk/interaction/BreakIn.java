package com.zarkonnen.cyberpunk.interaction;

import com.zarkonnen.cyberpunk.Person;
import com.zarkonnen.cyberpunk.Skill;
import com.zarkonnen.cyberpunk.Tile;
import java.util.Collections;
import java.util.List;

public class BreakIn extends AbstractInteraction<Tile> {
	public BreakIn(Person actor, Tile target) {
		super(actor, target);
	}
	
	@Override
	public String getName() {
		return "Break In";
	}

	@Override
	public String disabledReason() {
		return null;
	}

	@Override
	public String getDescription() {
		return "Get in using a combination of lockpicking and brute force.";
	}

	@Override
	public String run() {
		exhaust(10);
		if (actor().getSkill(Skill.BREAKING_AND_ENTERING) >= target().breakInResistance) {
			target().locked = false;
			return "You manage to get in.";
		} else {
			return "This place is locked down altogether too well.";
		}
	}
	
	public static class F implements InteractionFactory<Tile, BreakIn> {
		@Override
		public List<BreakIn> make(Person actor, Tile t) {
			if (t.lockedFor(actor)) {
				return Collections.singletonList(new BreakIn(actor, t));
			} else {
				return Collections.emptyList();
			}
		}
	}
}
