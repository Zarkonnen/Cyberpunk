package com.zarkonnen.cyberpunk.interaction;

import com.zarkonnen.cyberpunk.Person;
import com.zarkonnen.cyberpunk.Skill;
import com.zarkonnen.cyberpunk.Tile;

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
	public String description() {
		return "Get in by hacking into the door locks.";
	}

	@Override
	public String run() {
		if (actor().getSkill(Skill.HACKING) >= target().hackInResistance) {
			target().locked = false;
			return "You manage to get in.";
		} else {
			return "You are defeated by the locks.";
		}
	}
}
