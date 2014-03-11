package com.zarkonnen.cyberpunk.interaction;

import com.zarkonnen.cyberpunk.Person;
import com.zarkonnen.cyberpunk.Skill;
import com.zarkonnen.cyberpunk.Tile;
import java.util.Collections;
import java.util.List;

public class HackIn extends AbstractInteraction<Tile> {
	public HackIn(Person actor, Tile target) {
		super(actor, target);
	}
	
	@Override
	public String getName() {
		return "Hack In";
	}

	@Override
	public String disabledReason() {
		return null;
	}

	@Override
	public String getDescription() {
		return "Get in by hacking into the door locks.";
	}

	@Override
	public String run() {
		exhaust(3);
		if (actor().getSkill(Skill.HACKING) >= target().hackInResistance) {
			target().locked = false;
			return "You manage to get in.";
		} else {
			return "You are defeated by the locks.";
		}
	}
	
	public static class F implements InteractionFactory<Tile, HackIn> {
		@Override
		public List<HackIn> make(Person actor, Tile t) {
			if (t.lockedFor(actor)) {
				return Collections.singletonList(new HackIn(actor, t));
			} else {
				return Collections.emptyList();
			}
		}
	}
}
